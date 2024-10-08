package com.example.taskmanagementsystem.web.model;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Upsert user request", description = "Updating data in the User entity")
public class UpsertUserRequest {

    private String name;
    private String telephone;
    private String email;
    private String password;

}
