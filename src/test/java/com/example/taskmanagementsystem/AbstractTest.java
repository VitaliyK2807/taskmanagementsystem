package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.model.*;
import com.example.taskmanagementsystem.repositories.TasksRepository;
import com.example.taskmanagementsystem.repositories.UserRepository;
import com.example.taskmanagementsystem.service.TasksService;
import com.example.taskmanagementsystem.service.UserService;
import com.example.taskmanagementsystem.web.model.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
@Testcontainers
public class AbstractTest {

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected TasksService tasksService;

    @Autowired
    protected TasksRepository tasksRepository;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        userService.save(User.builder()
                .name("Test User")
                .email("TestUserMail@mail.abc")
                .telephone("+79226549487")
                .password("12345")
                .build(), Role.from(RoleType.ROLE_USER));

        userService.save(User.builder()
                .name("Test Admin")
                .email("TestAdminMail@mail.abc")
                .telephone("+79991112233")
                .password("11111")
                .build(), Role.from(RoleType.ROLE_ADMIN));

        tasksService.save(Tasks.builder()
                .name("Task test")
                .description("New task")
                .priority(Priority.AVERAGE)
                .status(Status.WAITING)
                .author(userService.findById(1L))
                .executor(userService.findById(2L))
                .commentary("-")
                .build());

    }


    @AfterEach
    public void afterEach() {
        tasksRepository.deleteAll();
        userRepository.deleteAll();
    }



}
