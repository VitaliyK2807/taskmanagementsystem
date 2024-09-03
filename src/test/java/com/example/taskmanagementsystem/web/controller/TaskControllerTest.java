package com.example.taskmanagementsystem.web.controller;

import com.example.taskmanagementsystem.AbstractTest;
import com.example.taskmanagementsystem.model.Priority;
import com.example.taskmanagementsystem.web.model.TaskResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.IOException;
import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest extends AbstractTest {

    @Test
    @WithMockUser(username = "TestUserMail@mail.abc", roles = {"USER"})
    public void taskReceiptTestWithUserRole_thenReturnOk() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/user/task/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getContentTaskResponse()));
    }

    private String getContentTaskResponse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();

        mapper.writeValue(writer,TaskResponse.builder()
                .id(1L)
                .task("Task test")
                .priority("AVERAGE")
                .status("WAITING")
                .build());
        return writer.toString();
    }
}
