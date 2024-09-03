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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Сотрудник с ID {0} не найден!", id
                )));
    }


    @Override
    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Username not found!"));
    }

    @Override
    public User save(User user, Role role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);

        role.setUser(createdUser);
        roleRepository.save(role);

        return createdUser;
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());

        BeanUtils.copyNonNullProperties(user, existedUser);

        return userRepository.save(existedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
