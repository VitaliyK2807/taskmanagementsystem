package com.example.taskmanagementsystem.web.model;

import com.example.taskmanagementsystem.model.Priority;
import com.example.taskmanagementsystem.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertTaskRequest {

    private String task;
    private String description;
    private Status status;
    private Priority priority;
    private Long authorId;
    private Long executorId;
    private String commentary;
}
