package com.example.taskmanagementsystem.web.controller;

import com.example.taskmanagementsystem.mapper.UserMapper;
import com.example.taskmanagementsystem.model.Role;
import com.example.taskmanagementsystem.model.RoleType;
import com.example.taskmanagementsystem.model.User;
import com.example.taskmanagementsystem.service.UserService;
import com.example.taskmanagementsystem.web.model.UpsertUserRequest;
import com.example.taskmanagementsystem.web.model.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
@Tag(name = "New User", description = "Creating a new user")
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(
            summary = "Creating a new user",
            description = "Creating a new user, return id, name, email, and list of authorship, list of executors"
    )
    @PostMapping("/account")
    public ResponseEntity<UserResponse> createUserAccount(@RequestBody UpsertUserRequest request,
                                                          @RequestParam RoleType roleType) {

        User newUser = userService.save(userMapper.requestToUser(request), Role.from(roleType));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

}
