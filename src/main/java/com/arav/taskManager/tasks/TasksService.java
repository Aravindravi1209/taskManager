package com.arav.taskManager.tasks;

import com.arav.taskManager.exceptions.EmptyTasksException;
import com.arav.taskManager.exceptions.NoSuchTaskExistException;
import com.arav.taskManager.notes.NotesEntity;
import com.arav.taskManager.notes.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final NotesRepository notesRepository;


    @Autowired
    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    public TaskEntity createTask(String title, String description, Date dueDate) {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setCompleted(false);
        task.setNotes(new ArrayList<>());
        return tasksRepository.save(task);
    }

    public List<TaskEntity> getTasks() throws EmptyTasksException {
        List<TaskEntity> tasks = tasksRepository.findAll();
        if(tasks.size()==0)
        {
            throw new EmptyTasksException("No tasks found");
        }
        return tasks;
    }

    public TaskEntity getTaskById(Long id)
    {
        return tasksRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> getTasksByCompletedStatus(Boolean status)
    {
        return tasksRepository.findByCompleted(status);
    }

    public TaskEntity markTaskAsCompleted(Long id) throws NoSuchTaskExistException {
        TaskEntity task = tasksRepository.findById(id).orElse(null);
        if(task==null)
        {
            throw new NoSuchTaskExistException("No such task exists");
        }
        task.setCompleted(true);
        return tasksRepository.save(task);
    }

    public void deleteTaskById(Long id)
    {
        List<NotesEntity> notes = tasksRepository.findById(id).get().getNotes();
        List<Long> toDelete = new ArrayList<>();
        for(NotesEntity notes1 : notes)
        {
            toDelete.add(notes1.getId());
        }
        notesRepository.deleteAllById(toDelete);
        tasksRepository.deleteById(id);
    }

}
