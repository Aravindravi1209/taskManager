package com.arav.taskManager.tasks;

import com.arav.taskManager.notes.NotesEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tasks")
public class TaskEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String description;
    private Boolean completed;
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    @OneToMany(mappedBy = "task")
    private List<NotesEntity> notes;



}
