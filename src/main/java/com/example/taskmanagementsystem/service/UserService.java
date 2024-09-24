package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.model.Role;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.model.User;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUserEmail(String username);

    User save(User user, Role role);

    User update(User user);

    void deleteById(Long id);

    User saveWithTasks(User user, List<Tasks> tasks);
}
