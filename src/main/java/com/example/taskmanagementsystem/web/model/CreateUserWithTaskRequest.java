package com.example.taskmanagementsystem.web.model;

import com.example.taskmanagementsystem.model.Tasks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserWithTaskRequest {

    private String name;
    private String telephone;
    private String email;
    private String password;

    private List<TaskRequest> tasks;
}
