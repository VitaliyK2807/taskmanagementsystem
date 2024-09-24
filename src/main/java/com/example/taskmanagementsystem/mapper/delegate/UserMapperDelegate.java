package com.example.taskmanagementsystem.mapper.delegate;

import com.example.taskmanagementsystem.mapper.UserMapper;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.model.User;
import com.example.taskmanagementsystem.web.model.CreateUserWithTaskRequest;
import com.example.taskmanagementsystem.web.model.TaskResponse;
import com.example.taskmanagementsystem.web.model.UpsertUserRequest;
import com.example.taskmanagementsystem.web.model.UserResponse;

import java.util.List;

public abstract class UserMapperDelegate implements UserMapper {



    @Override
    public User requestToUser(UpsertUserRequest request) {
        return User.builder()
                .name(request.getName())
                .telephone(request.getTelephone())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    @Override
    public User requestToUser(CreateUserWithTaskRequest request) {
        return User.builder()
                .name(request.getName())
                .telephone(request.getTelephone())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    @Override
    public User requestToUser(Long userId, UpsertUserRequest request) {
        User user = requestToUser(request);
        user.setId(userId);

        return user;
    }



    @Override
    public UserResponse userToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .authorship(getTasks(user.getAuthorship()))
                .executors(getTasks(user.getExecutors()))
                .build();
    }

    private List<TaskResponse> getTasks(List<Tasks> tasks) {

        return tasks.stream().map(this::taskToResponse).toList();
    }

    private TaskResponse taskToResponse(Tasks task) {
        return TaskResponse.builder()
                .id(task.getId())
                .task(task.getName())
                .status(task.getStatus().name())
                .priority(task.getPriority().name())
                .build();
    }

}
