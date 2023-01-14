package com.example.demo;

import com.example.TodoStudy.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class TodoControllerTest {

    @Autowired
    MockMvc mvc;

    private TodoService service;


    @Test
    void createTodo() {

    }

    @Test
    void selectTodo() {
    }

    @Test
    void updateTodo() {
    }

    @Test
    void deleteTodo() {
    }
}