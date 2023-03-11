package com.arav.taskManager.tasks.dtos;

import com.arav.taskManager.notes.NotesEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateTaskResponseDto {
    private Long id;

    private String title;
    private String description;
    private Boolean completed;
    private Date dueDate;

    private List<NotesEntity> notes;

}
