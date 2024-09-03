package com.example.taskmanagementsystem.web.model;

import com.example.taskmanagementsystem.model.Priority;
import com.example.taskmanagementsystem.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskFilterAuthor {

    private Integer pageSize;
    private Integer pageNumber;
    private Long authorId;
    private String priority;
    private String status;
}
