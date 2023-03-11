package com.arav.taskManager.tasks;

import com.arav.taskManager.common.ErrorResponseDto;
import com.arav.taskManager.exceptions.EmptyTasksException;
import com.arav.taskManager.exceptions.NoSuchTaskExistException;
import com.arav.taskManager.tasks.dtos.CreateTaskRequestDto;
import com.arav.taskManager.tasks.dtos.CreateTaskResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    //GETTING ALL TASKS
    @GetMapping("/all")
    public List<TaskEntity> getAllTasks() throws EmptyTasksException {
        return tasksService.getTasks();
    }

    //GETTING TASK BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CreateTaskResponseDto> getTaskById(@PathVariable("id") Long id)
    {
        CreateTaskResponseDto responseDto = tasksService.getTaskById(id);
        return ResponseEntity
                .ok()
                .body(responseDto);
    }

    //GETTING TASKS BY COMPLETED STATUS FILTER
    @GetMapping("/status")
    public List<TaskEntity> getTasksByCompletedStatus(@RequestParam("completed") Boolean status)
    {
        return tasksService.getTasksByCompletedStatus(status);
    }

    //CREATION OF TASKS
    @PostMapping("/add")
    public ResponseEntity<CreateTaskResponseDto> createTask(@RequestBody CreateTaskRequestDto requestDto)
    {
        CreateTaskResponseDto responseDto = tasksService.createTask(requestDto);
        return ResponseEntity
                .created(URI.create("/tasks/"+responseDto.getId()))
                .body(responseDto);
    }

    //MARKING TASK AS COMPLETED
    @PatchMapping("/{id}/complete")
    public TaskEntity markTaskAsCompleted(@PathVariable("id") Long id) throws NoSuchTaskExistException {
        return tasksService.markTaskAsCompleted(id);
    }

    //DELETING TASK BY ID
    @DeleteMapping("/{id}/delete")
    public void deleteTaskById(@PathVariable("id") Long id)
    {
        tasksService.deleteTaskById(id);
    }

    @ExceptionHandler({IllegalArgumentException.class,
            EmptyTasksException.class,
            NoSuchTaskExistException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponseDto> handleException(Exception e)
    {
        if(e instanceof NoSuchTaskExistException)
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ErrorResponseDto(e.getMessage()));

        if(e instanceof HttpMessageNotReadableException)
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseDto("Invalid request body"));

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDto(e.getMessage()));
    }

}
