package com.example.demo.service;

import com.example.TodoStudy.dto.TodoDTO;
import com.example.TodoStudy.model.TodoEntity;
import com.example.TodoStudy.persistence.TodoRepository;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

class TodoServiceTest {
    @MockBean
    private TodoRepository repository = Mockito.mock(TodoRepository.class);
    @Autowired
    private TodoEntity entity;

    @Test
    void create() {
        TodoEntity givenProduct = new TodoEntity();
        givenProduct.setId(entity.getId());


        verify(repository).findByUserId(entity.getUserId());
    }

    @Test
    void select() {
        TodoEntity givenProduct = new TodoEntity();
        givenProduct.setId(entity.getId());
        givenProduct.setUserId(entity.getUserId());
        givenProduct.setTitle(entity.getTitle());
        givenProduct.setDone(entity.isDone());

        Assert.assertEquals(givenProduct.setId(entity.getId()), TodoDTO.toEntity());
        Assert.assertEquals(entity.getUserId(), TodoDTO.toEntity(TodoDTO.builder().build()));


        verify(repository).selectByUserId(entity.getUserId());
    }

    @Test
    void update() {
        ass



        verify(repository).updateByUserId(entity.getUserId());
    }

    @Test
    void delete() {

    }

    @Test
    void validate() {
       assertEquals.
    }
}