package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private TodoService service;

    @BeforeEach
    void setUp() {
        System.out.println("-- BeforeEach 어노테이션 호출 --");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testService() {
    }

    @Test
    @DisplayName("Create 테스트")
    void create() {
        System.out.println("-- Create 테스트 시작 --");

        //given
        TodoEntity entity1 = TodoEntity.builder()
                .userId("Test UserId")
                .title("New Title")
                .done(true)
                .build();

        //when
        List<TodoEntity> createEntity = service.create(entity1);

        //then
        assertEquals(createEntity, repository.findByUserId("Test UserId"));
    }

    @Test
    @DisplayName("retrieve 테스트")
    void retrieve() {
        System.out.println("-- retrieve 테스트 시작 --");

        //given
        TodoEntity entity1 = TodoEntity.builder()
                .userId("Test UserId")
                .title("New Title1")
                .done(true)
                .build();
        repository.save(entity1);

        TodoEntity entity2 = TodoEntity.builder()
                .userId("Test UserId")
                .title("New Title2")
                .done(false)
                .build();
        repository.save(entity2);


        //when
        List<TodoEntity> entityList = service.retrieve("Test UserId");

        //then
        System.out.println(entityList);
        assertEquals(2,entityList.size());
    }

    @Test
    void update() throws Exception {
        System.out.println("-- Update 테스트 시작 --");

        //given
        TodoEntity entity = TodoEntity.builder()
                .userId("Test UserId")
                .title("New Title")
                .done(true)
                .build();
        repository.save(entity);

        //when
        List<TodoEntity> updatedEntity = service.update(entity);

        //then
        assertEquals(updatedEntity, repository.findByUserId(entity.getUserId()));
    }

    @Test
    void delete() {
        System.out.println("-- Delete 테스트 시작 --");

        //given
        TodoEntity entity = TodoEntity.builder()
                .userId("Test UserId")
                .title("New Title1")
                .done(true)
                .build();
        repository.save(entity);

        TodoEntity entity1 = TodoEntity.builder()
                .userId("Test UserId")
                .title("New Title2")
                .done(false)
                .build();
        repository.save(entity1);

        //when
        List deletedEntity = service.delete(entity);

        //then
        System.out.println(deletedEntity);
    }

}
