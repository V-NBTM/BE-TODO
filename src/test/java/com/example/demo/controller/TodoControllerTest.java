package com.example.demo.controller;

import com.example.demo.dto.TodoDTO;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.demo.dto.TodoDTO.*;
import static org.springframework.mock.web.reactive.function.server.MockServerRequest.builder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TodoControllerTest.class)
class TodoControllerTest {

    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc mockMvc;

    private static final String userId = "temporary-user";

    @Test
    @DisplayName("createTodo 테스트")
    void createTodo() throws Exception {


        mockMvc.perform(post("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.isDone").exists())
                .andDo(print());

    }

    @Test
    void selectTodo() throws Exception {
        String todo = "todo";

        mockMvc.perform(get("/todo"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.isDone").exists())
                .andDo(print());
    }

    @Test
    void updateTodo() throws Exception {

        mockMvc.perform(put("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.isDone").exists())
                .andDo(print());
    }

    @Test
    void deleteTodo() throws Exception {
        String todo = "todo";

        mockMvc.perform(delete("/todo"))
                .andExpect(status().isOk())
                .andExpect(content().string(todo));
    }
}