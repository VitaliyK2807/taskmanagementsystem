package com.example.taskmanagementsystem.web.controller;

import com.example.taskmanagementsystem.AbstractTest;
import com.example.taskmanagementsystem.web.model.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class CreateUserControllerTest extends AbstractTest {

    @Test
    @WithMockUser(username = "TestUserMail@mail.abc", roles = {"USER"})
    public void whenUserRequestMethodWithUserRole_thenReturnOk() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getContentRoleUser()));
    }

    @Test
    @WithMockUser(username = "TestAdminMail@mail.abc", roles = {"ADMIN"})
    public void whenUserRequestMethodWithAdminRole_thenReturnOk() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/user/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(getContentRoleAdmin()));
    }




    private String getContentRoleAdmin() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();

        mapper.writeValue(writer, new UserResponse().builder()
                .id(2L)
                .name("Test Admin")
                .email("TestAdminMail@mail.abc")
                .executors(List.of())
                .authorship(List.of())
                .build());

        return writer.toString();
    }

    private String getContentRoleUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();

        mapper.writeValue(writer, new UserResponse().builder()
                .id(1L)
                .name("Test User")
                .email("TestUserMail@mail.abc")
                .executors(List.of())
                .authorship(List.of())
                .build());

        return writer.toString();
    }

    private String getContentEmailUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();

        mapper.writeValue(writer, new UserResponse().builder()
                .id(1L)
                .name("Test User")
                .email("TestUserMail@mail.abc")
                .executors(List.of())
                .authorship(List.of())
                .build());

        return writer.toString();
    }
}
