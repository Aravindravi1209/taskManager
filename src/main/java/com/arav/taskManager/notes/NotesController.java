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

    @PostMapping("/notes")
    public NotesEntity createNote(@PathVariable("id") Long id, @RequestBody NotesEntity notesEntity)
    {
        return notesService.createNote(id, notesEntity.getTitle(), notesEntity.getDescription());
    }

    @GetMapping("/notes")
    public List<NotesEntity> getNotesById(@PathVariable("id") Long id)
    {
        return notesService.getNotesById(id);
    }
}
