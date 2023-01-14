package com.example.jae.service;

import com.example.jae.model.TodoEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoServiceTest {

    @Autowired
    private TodoService service;

    private static TodoEntity entity01;
    private static TodoEntity entity02;
    private static TodoEntity entity03;


    @BeforeAll
    static void setUp() {
        entity01 = TodoEntity.builder().title("새 포스트 01").userId("temporary-user").build();
        entity02 = TodoEntity.builder().title("새 포스트 02").build();
    }

    //@AfterEach
    //void tearDown() {
    //}

    @Test
    void create() {
        System.out.println("## Create Todo 시작 ##");
        System.out.println("entity01 before create : " + entity01);

        List<TodoEntity> entities = service.create(entity01);

        System.out.println("entity01 after create : " + entity01);

        assertEquals(entities.get(0).getTitle(), entity01.getTitle());
        assertThrows(RuntimeException.class, () -> service.create(entity02));
        assertThrows(RuntimeException.class, () -> service.create(entity03));

        System.out.println("## Create Todo 종료 ##");
    }

    @Test
    void read() {
        System.out.println("## Read Todo 시작 ##");
        TodoEntity entity = TodoEntity.builder().userId("temporary-user").build();

        List<TodoEntity> entities = service.read(entity);
        System.out.println("entities : " + entities);
        System.out.println("## Read Todo 종료 ##");
    }

    @Test
    void update() {
        System.out.println("## Update Todo 시작 ##");
        entity01.setTitle("title 01 modified");

        List<TodoEntity> entities = service.update(entity01);

        assertEquals(entities.get(0).getTitle(), "title 01 modified");

        System.out.println("## Update Todo 시작 ##");
    }

    @Test
    void delete() {
        System.out.println("## Delete Todo 시작 ##");
        System.out.println("entity01 to delete : " + entity01);
        String message = service.delete(entity01);

        assertEquals(message, String.format("Entity Id %s was deleted successfully", entity01.getId()));
        assertThrows(RuntimeException.class, () -> service.delete(entity02));

        System.out.println("## Delete Todo 시작 ##");
    }
}