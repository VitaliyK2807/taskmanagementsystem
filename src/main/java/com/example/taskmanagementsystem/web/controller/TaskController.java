package com.example.taskmanagementsystem.web.controller;

import com.example.taskmanagementsystem.mapper.TasksMapper;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.service.TasksService;
import com.example.taskmanagementsystem.web.model.TaskFilterAuthor;
import com.example.taskmanagementsystem.web.model.TaskResponse;
import com.example.taskmanagementsystem.web.model.TasksListResponse;
import com.example.taskmanagementsystem.web.model.UpsertTaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/task")
@RequiredArgsConstructor
@Tag(name = "Admin controller", description = "Admin controller for working with task entities")
public class TaskController {

    private final TasksService tasksService;

    private final TasksMapper mapper;

    @Operation(
            summary = "Getting a filtered list of tasks",
            description = "Get list of tasks"
    )
    @GetMapping("/filter")
    public ResponseEntity<TasksListResponse> filterByAuthor(TaskFilterAuthor filter) {
        return ResponseEntity.ok(
                mapper.taskListToTaskListResponse(tasksService.filterByAuthor(filter))
        );
    }

    @Operation(
            summary = "Get list of tasks",
            description = "Get list of tasks"
    )
    @GetMapping
    public ResponseEntity<TasksListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.taskListToTaskListResponse(tasksService.findAll())
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
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.taskToResponse(tasksService.findById(id))
        );
    }

    @Operation(
            summary = "Creating a new task",
            description = "Creating a new user, return id, name, status, priority"
    )
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody UpsertTaskRequest request) {
        Tasks newTask = tasksService.save(mapper.requestToTask(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.taskToResponse(newTask));
    }

    @Operation(
            summary = "Updating an entity by id",
            description = "Updating an entity by id"
    )
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable("id") Long taskId,
                                               @RequestBody UpsertTaskRequest request) {
        Tasks updatedTask = tasksService.update(mapper.requestToTask(taskId, request));

        return ResponseEntity.ok(mapper.taskToResponse(updatedTask));
    }

    @Operation(
            summary = "Delete task by ID",
            description = "Delete task by ID"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tasksService.deleteById(id);

        return ResponseEntity.noContent().build();
    }



}
