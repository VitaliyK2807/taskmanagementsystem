package com.example.taskmanagementsystem.web.controller;

import com.example.taskmanagementsystem.mapper.TasksMapper;
import com.example.taskmanagementsystem.mapper.UserMapper;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.service.TasksService;
import com.example.taskmanagementsystem.service.UserService;
import com.example.taskmanagementsystem.web.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User controller")
public class UserController {

    private final TasksService tasksService;
    private final UserService userService;
    private final TasksMapper tasksMapper;
    private final UserMapper userMapper;

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
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @Operation(
            summary = "Getting a filtered list of tasks",
            description = "Get list of tasks"
    )
    @GetMapping("/task/filter")
    public ResponseEntity<TasksListResponse> filterByAuthor(TaskFilterAuthor filter) {
        return ResponseEntity.ok(
                tasksMapper.taskListToTaskListResponse(tasksService.filterByAuthor(filter))
        );
    }

    @Operation(
            summary = "Get list of tasks",
            description = "Get list of tasks"
    )
    @GetMapping("/tasks")
    public ResponseEntity<TasksListResponse> findAll() {
        return ResponseEntity.ok(
                tasksMapper.taskListToTaskListResponse(tasksService.findAll())
        );
    }

    @Operation(
            summary = "Get task by ID",
            description = "Get task by ID, return id, name, status, priority"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = TaskResponse.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponse> findByTaskId(@PathVariable Long id) {
        return ResponseEntity.ok(
                tasksMapper.taskToResponse(tasksService.findById(id))
        );
    }

    @Operation(
            summary = "Updating an entity by id",
            description = "Updating an entity by id"
    )
    @PutMapping("/task/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long taskId,
                                               @RequestBody UpsertTaskRequest request) {
        Tasks updatedTask = tasksService.update(tasksMapper.requestToTask(taskId, request));

        return ResponseEntity.ok(tasksMapper.taskToResponse(updatedTask));
    }

}
