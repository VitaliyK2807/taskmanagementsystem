package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.excepcions.EntityNotFoundException;
import com.example.taskmanagementsystem.model.Role;
import com.example.taskmanagementsystem.model.User;
import com.example.taskmanagementsystem.repositories.RoleRepository;
import com.example.taskmanagementsystem.repositories.UserRepository;
import com.example.taskmanagementsystem.service.RoleService;
import com.example.taskmanagementsystem.service.UserService;
import com.example.taskmanagementsystem.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Роль с ID {0} не найдена!", id
                )));
    }


    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
