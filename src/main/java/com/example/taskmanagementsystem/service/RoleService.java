package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    void deleteById(Long id);
}
