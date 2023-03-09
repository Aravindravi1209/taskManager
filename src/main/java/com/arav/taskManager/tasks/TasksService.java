package com.arav.taskManager.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;


    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public TaskEntity createTask(String title, String description, Date dueDate) {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setCompleted(false);
        return tasksRepository.save(task);
    }

    public List<TaskEntity> getTasks()
    {
        return tasksRepository.findAll();
    }

    public TaskEntity getTaskById(Long id)
    {
        return tasksRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> getTasksByCompletedStatus(Boolean status)
    {
        return tasksRepository.findByCompleted(status);
    }

}
