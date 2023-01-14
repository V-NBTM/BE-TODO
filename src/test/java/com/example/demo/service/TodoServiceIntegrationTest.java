package com.example.demo.service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TodoServiceIntegrationTest {
    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    private static final String userid = "temporary-user";

    @Test
    @DisplayName("Todo 생성 테스트")
    @Transactional
    void createTodo() {
//        TodoDTO dto = TodoDTO.builder()
//                .id("id")
//                .title("title")
//                .done(true)
//                .build();
        TodoDTO dto = new TodoDTO("id", "title", true);

        TodoEntity entity = TodoDTO.toEntity(dto);
        entity.setUserId(userid);

        List<TodoEntity> todo = todoService.create(entity);

        assertEquals(entity.getUserId(), todo.get(0).getUserId());
    }

    @Test
    @DisplayName("userId의 Todo 리스트 조회 테스트")
    @Transactional
    void readTodoList() {
        List<TodoEntity> entityList = todoRepository.saveAll(List.of(
                TodoEntity.builder()
                        .id("1")
                        .userId(userid)
                        .title("title 1")
                        .done(true)
                        .build(),
                TodoEntity.builder()
                        .id("2")
                        .userId(userid)
                        .title("title 2")
                        .done(false)
                        .build()
        ));

        List<TodoEntity> list = todoService.retrieve(userid);

        assertEquals(list.size(), entityList.size());
        assertEquals(list.get(0).getId(), entityList.get(0).getId());
    }

    @Test
    @DisplayName("Todo 수정 테스트")
    @Transactional
    void updateTodo() {
        TodoEntity beforeEntity = todoRepository.save(TodoEntity.builder()
                .id("123")
                .userId(userid)
                .title("title 1")
                .done(true)
                .build());
        TodoEntity afterEntity = TodoEntity.builder()
                .id(beforeEntity.getId())
                .userId(userid)
                .title("title 2")
                .done(false)
                .build();

        List<TodoEntity> todoEntity = todoService.update(afterEntity);

        assertEquals(todoEntity.get(0).getId(), afterEntity.getId());
        assertEquals(todoEntity.get(0).getTitle(), afterEntity.getTitle());
        assertEquals(todoEntity.get(0).isDone(), afterEntity.isDone());
    }

    @Test
    @DisplayName("Todo 삭제 테스트")
    @Transactional
    void deleteTodo() {
        TodoEntity entity = todoRepository.save(TodoEntity.builder()
                        .id("did")
                        .userId(userid)
                        .title("title 133")
                        .done(false)
                .build());

        List<TodoEntity> delete = todoService.delete(entity);

        assertFalse(todoRepository.findById(entity.getId()).isPresent());
    }
}