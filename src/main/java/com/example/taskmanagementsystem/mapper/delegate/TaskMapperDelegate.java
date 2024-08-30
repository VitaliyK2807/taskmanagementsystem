package com.example.taskmanagementsystem.mapper.delegate;

import com.example.taskmanagementsystem.mapper.TasksMapper;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.service.UserService;
import com.example.taskmanagementsystem.web.model.TaskResponse;
import com.example.taskmanagementsystem.web.model.TasksListResponse;
import com.example.taskmanagementsystem.web.model.UpsertTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class TaskMapperDelegate implements TasksMapper {

    @Autowired
    private UserService userService;

    @Override
    public List<TaskResponse> taskListToResponseList(List<Tasks> tasks) {
        return tasks.stream().map(this::taskToResponse).toList();
    }

    @Override
    public Tasks requestToTask(UpsertTaskRequest request) {
        return Tasks.builder()
                .name(request.getTask())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .author(userService.findById(request.getAuthorId()))
                .executor(userService.findById(request.getExecutorId()))
                .commentary(request.getCommentary())
                .build();
    }

    @Override
    public Tasks requestToTask(Long taskId, UpsertTaskRequest request) {
        Tasks task = requestToTask(request);
        task.setId(taskId);

        return task;
    }

    @Override
    public TaskResponse taskToResponse(Tasks task) {
        return TaskResponse.builder()
                .id(task.getId())
                .task(task.getName())
                .status(task.getStatus().name())
                .priority(task.getPriority().name())
                .build();
    }

}
