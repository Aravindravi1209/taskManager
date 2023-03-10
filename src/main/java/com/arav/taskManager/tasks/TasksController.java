package com.arav.taskManager.tasks;

import com.arav.taskManager.exceptions.EmptyTasksException;
import com.arav.taskManager.exceptions.NoSuchTaskExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TaskEntity getTaskById(@PathVariable("id") Long id)
    {
        return tasksService.getTaskById(id);
    }

    //GETTING TASKS BY COMPLETED STATUS FILTER
    @GetMapping("/status")
    public List<TaskEntity> getTasksByCompletedStatus(@RequestParam("completed") Boolean status)
    {
        return tasksService.getTasksByCompletedStatus(status);
    }

    //CREATION OF TASKS
    @PostMapping("/add")
    public TaskEntity createTask(@RequestBody TaskEntity taskEntity)
    {
        return tasksService.createTask(taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getDueDate());
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

}
