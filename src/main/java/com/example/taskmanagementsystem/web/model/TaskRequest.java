package com.example.taskmanagementsystem.web.model;


import com.example.taskmanagementsystem.model.Priority;
import com.example.taskmanagementsystem.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    private String task;
    private String description;
    private Status status;
    private Priority priority;
    private Long executorId;
    private String commentary;

}
