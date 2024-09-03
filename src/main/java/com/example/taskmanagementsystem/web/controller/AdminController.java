package com.example.taskmanagementsystem.web.controller;

import com.example.taskmanagementsystem.mapper.UserMapper;
import com.example.taskmanagementsystem.model.User;
import com.example.taskmanagementsystem.service.RoleService;
import com.example.taskmanagementsystem.service.UserService;
import com.example.taskmanagementsystem.web.model.ErrorResponse;
import com.example.taskmanagementsystem.web.model.UpsertUserRequest;
import com.example.taskmanagementsystem.web.model.UserListResponse;
import com.example.taskmanagementsystem.web.model.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin controller")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Operation(
            summary = "Get list of users",
            description = "Get list of users"
    )
    @GetMapping("/users")
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(
                userMapper.userListToUserListResponse(userService.findAll())
        );
    }

    @Operation(
            summary = "Get user by ID",
            description = "Get user by ID, return id, name, email, and list of authorship, list of executors"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @Operation(
            summary = "Update user by ID",
            description = "Update user by ID, return id, name, email, and list of authorship, list of executors"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
                    }
            )
    })

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long userId,
                                               @RequestBody UpsertUserRequest request) {
        User updatedUser = userService.update(userMapper.requestToUser(userId, request));

        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }


    @Operation(
            summary = "Delete user by ID",
            description = "Delete user by ID"
    )
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }



}
