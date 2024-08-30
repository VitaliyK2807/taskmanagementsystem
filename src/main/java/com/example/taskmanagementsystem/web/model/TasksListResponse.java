package com.example.taskmanagementsystem.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TasksListResponse {

    private List<TaskResponse> tasks = new ArrayList<>();
}
