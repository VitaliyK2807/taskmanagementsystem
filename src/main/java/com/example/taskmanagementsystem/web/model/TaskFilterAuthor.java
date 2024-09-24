package com.example.taskmanagementsystem.web.model;

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
