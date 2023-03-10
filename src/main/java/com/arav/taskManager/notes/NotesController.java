package com.arav.taskManager.notes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{id}")
public class NotesController {
    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    //CREATE NOTES UNDER A TASK ID
    @PostMapping("/notes")
    public NotesEntity createNote(@PathVariable("id") Long id, @RequestBody NotesEntity notesEntity)
    {
        return notesService.createNote(id, notesEntity.getTitle(), notesEntity.getDescription());
    }

    //GET NOTES BY TASK ID
    @GetMapping("/notes")
    public List<NotesEntity> getNotesById(@PathVariable("id") Long id)
    {
        return notesService.getNotesById(id);
    }

    //GET NOTES BY TASK ID AND NOTE ID
    @GetMapping("/notes/{noteId}")
    public NotesEntity getNoteById(@PathVariable("id") Long id, @PathVariable("noteId") Long noteId)
    {
        return notesService.getNoteByNotesId(id, noteId);
    }

    @DeleteMapping("/notes/{noteId}")
    public void deleteNoteById(@PathVariable("id") Long id, @PathVariable("noteId") Long noteId)
    {
        notesService.deleteNoteById(id, noteId);
    }
}
