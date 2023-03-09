package com.arav.taskManager.tasks;

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
    public List<TaskEntity> getAllTasks()
    {
        return tasksService.getTasks();
    }

    //GETTING TASK BY ID
    @GetMapping("/{id}")
    public TaskEntity getTaskById(@PathVariable("id") Long id)
    {
        return tasksService.getTaskById(id);
    }

    //GETTING TASKS BY COMPLETED STATUS FILTER
    @GetMapping("")
    public List<TaskEntity> getTasksByCompletedStatus(@RequestParam("completed") Boolean status)
    {
        return tasksService.getTasksByCompletedStatus(status);
    }

    //CREATION OF TASKS
    @PostMapping("")
    public TaskEntity createTask(@RequestBody TaskEntity taskEntity)
    {
        return tasksService.createTask(taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getDueDate());
    }

}
