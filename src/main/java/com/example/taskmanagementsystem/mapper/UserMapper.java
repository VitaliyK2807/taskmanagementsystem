package com.example.taskmanagementsystem.mapper;

import com.example.taskmanagementsystem.mapper.delegate.UserMapperDelegate;
import com.example.taskmanagementsystem.model.User;
import com.example.taskmanagementsystem.web.model.CreateUserWithTaskRequest;
import com.example.taskmanagementsystem.web.model.UpsertUserRequest;
import com.example.taskmanagementsystem.web.model.UserListResponse;
import com.example.taskmanagementsystem.web.model.UserResponse;
import org.mapstruct.*;

import java.util.List;

@DecoratedWith(UserMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {TasksMapper.class})
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    User requestToUser(CreateUserWithTaskRequest request);

    default UserListResponse userListToUserListResponse(List<User> users) {
        UserListResponse response = new UserListResponse();

        response.setUsers(users.stream().map(this::userToResponse).toList());

        return response;
    }
}
