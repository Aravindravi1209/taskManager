package com.arav.taskManager.tasks;

import com.arav.taskManager.exceptions.EmptyTasksException;
import com.arav.taskManager.exceptions.NoSuchTaskExistException;
import com.arav.taskManager.notes.NotesEntity;
import com.arav.taskManager.notes.NotesRepository;
import com.arav.taskManager.tasks.dtos.CreateTaskRequestDto;
import com.arav.taskManager.tasks.dtos.CreateTaskResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final NotesRepository notesRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository, ModelMapper modelMapper) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
        this.modelMapper = modelMapper;
    }

    public CreateTaskResponseDto createTask(CreateTaskRequestDto newTask) {
        //Date Validation
        if(newTask.getDueDate().before(new Date()))
        {
            throw new IllegalArgumentException("Due date cannot be before today");
        }
        TaskEntity task = modelMapper.map(newTask, TaskEntity.class);
        task.setCompleted(false);
        task.setNotes(new ArrayList<>());
        TaskEntity savedTask = tasksRepository.save(task);
        return modelMapper.map(savedTask, CreateTaskResponseDto.class);
    }

    public List<TaskEntity> getTasks() throws EmptyTasksException {
        List<TaskEntity> tasks = tasksRepository.findAll();
        if(tasks.size()==0)
        {
            throw new EmptyTasksException("No tasks exist");
        }
        return tasks;
    }

    public CreateTaskResponseDto getTaskById(Long id) throws NoSuchTaskExistException {
        TaskEntity task = tasksRepository.findById(id).orElseThrow(() -> new NoSuchTaskExistException(id));
        return modelMapper.map(task, CreateTaskResponseDto.class);
    }

    public List<TaskEntity> getTasksByCompletedStatus(Boolean status)
    {
        return tasksRepository.findByCompleted(status);
    }

    public TaskEntity markTaskAsCompleted(Long id) throws NoSuchTaskExistException {
        TaskEntity task = tasksRepository.findById(id).orElse(null);
        if(task==null)
        {
            throw new NoSuchTaskExistException(id);
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
