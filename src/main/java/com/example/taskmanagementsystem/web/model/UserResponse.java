package com.example.taskmanagementsystem.web.model;

import com.example.taskmanagementsystem.model.Tasks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private List<TaskResponse> authorship = new ArrayList<>();

    private List<TaskResponse> executors = new ArrayList<>();
}
