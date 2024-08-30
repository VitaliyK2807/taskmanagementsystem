package com.example.taskmanagementsystem.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {

    private String name;
    private String telephone;
    private String email;
    private String password;

}
