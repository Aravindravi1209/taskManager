package com.arav.taskManager.tasks.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class CreateTaskRequestDto {
    @NonNull
    String title;
    String description;
    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    Date dueDate;
}
