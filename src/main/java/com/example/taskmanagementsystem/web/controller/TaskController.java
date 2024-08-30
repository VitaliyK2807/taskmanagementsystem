package com.example.taskmanagementsystem.web.controller;

import com.example.taskmanagementsystem.mapper.TasksMapper;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.service.TasksService;
import com.example.taskmanagementsystem.web.model.TaskFilterAuthor;
import com.example.taskmanagementsystem.web.model.TaskResponse;
import com.example.taskmanagementsystem.web.model.TasksListResponse;
import com.example.taskmanagementsystem.web.model.UpsertTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TasksService tasksService;

    private final TasksMapper mapper;

    @GetMapping("/filter")
    public ResponseEntity<TasksListResponse> filterByAuthor(@RequestParam Long authorId) {
        TaskFilterAuthor filter = new TaskFilterAuthor();
        filter.setUserId(authorId);
        return ResponseEntity.ok(
                mapper.taskListToTaskListResponse(tasksService.filterByAuthor(filter))
        );
    }

    @GetMapping
    public ResponseEntity<TasksListResponse> findAll() {
        return ResponseEntity.ok(
                mapper.taskListToTaskListResponse(tasksService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.taskToResponse(tasksService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody UpsertTaskRequest request) {
        Tasks newTask = tasksService.save(mapper.requestToTask(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.taskToResponse(newTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable("id") Long taskId,
                                               @RequestBody UpsertTaskRequest request) {
        Tasks updatedTask = tasksService.update(mapper.requestToTask(taskId, request));

        return ResponseEntity.ok(mapper.taskToResponse(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tasksService.deleteById(id);

        return ResponseEntity.noContent().build();
    }



}
