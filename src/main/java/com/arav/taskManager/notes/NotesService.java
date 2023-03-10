package com.arav.taskManager.notes;

import com.arav.taskManager.tasks.TaskEntity;
import com.arav.taskManager.tasks.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotesService {
    private final NotesRepository notesRepository;
    private final TasksRepository tasksRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository, TasksRepository tasksRepository) {
        this.notesRepository = notesRepository;
        this.tasksRepository = tasksRepository;
    }

    public NotesEntity createNote(Long id, String title, String description)
    {
        TaskEntity task = tasksRepository.findById(id).orElse(null);
        List<NotesEntity> notes = task.getNotes();
        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setTitle(title);
        notesEntity.setDescription(description);
        notesEntity.setTask(task);
        notes.add(notesEntity);
        task.setNotes(notes);
        tasksRepository.save(task);
        return notesRepository.save(notesEntity);
    }

    public List<NotesEntity> getNotesById(Long id)
    {
        return tasksRepository.findById(id).get().getNotes();
    }

    public NotesEntity getNoteByNotesId(Long id, Long noteId)
    {
        List<NotesEntity> notes = tasksRepository.findById(id).get().getNotes();
        if(noteId>=notes.size())
        {
            throw new IndexOutOfBoundsException("Note not found");
        }
        return notes.get(noteId.intValue());
    }

    public void deleteNoteById(Long id, Long noteId)
    {
        List<NotesEntity> notes = tasksRepository.findById(id).get().getNotes();
        if(noteId>=notes.size())
        {
            throw new IndexOutOfBoundsException("Note not found");
        }
        notesRepository.delete(notes.get(noteId.intValue()));
    }
}
