package com.arav.taskManager.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class TasksServiceTests {

    @Autowired
    private TasksRepository tasksRepository;

    @Test
    public void testCreateTask()
    {
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = tasksService.createTask("title", "description", new Date());
        System.out.println(task);
    }
}
