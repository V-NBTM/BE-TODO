package com.example.demo.controller;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
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

    @Test
    @DisplayName("controller createTodo 테스트")
    void createTodo() throws Exception {
        TodoDTO dto = new TodoDTO("id", "title", true);

        TodoEntity entity = TodoDTO.toEntity(dto);

        given(todoService.create(entity));

        mockMvc.perform(post("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.isDone").exists())
                .andDo(print());

    }

    @Test
    @DisplayName("controller selectTodo 테스트")
    void selectTodo() throws Exception {
        TodoDTO dto = new TodoDTO("id", "title", true);

        TodoEntity entity = TodoDTO.toEntity(dto);

        given(todoService.select(entity));

        mockMvc.perform(get("/todo"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.isDone").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("controller updateTodo 테스트")
    void updateTodo() throws Exception {
        TodoDTO dto = new TodoDTO("id", "title", true);

        TodoEntity entity = TodoDTO.toEntity(dto);

        given(todoService.update(entity));

        mockMvc.perform(put("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.isDone").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("controller deleteTodo 테스트")
    void deleteTodo() throws Exception {
        TodoDTO dto = new TodoDTO("id", "title", true);

        TodoEntity entity = TodoDTO.toEntity(dto);

        given(todoService.delete(entity));

        mockMvc.perform(delete("/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("/todo"));
    }
}