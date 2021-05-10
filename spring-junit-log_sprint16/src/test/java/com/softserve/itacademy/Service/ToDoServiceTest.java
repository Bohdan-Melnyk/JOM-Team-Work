package com.softserve.itacademy.service;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ToDoServiceTest {

    ToDoService toDoService;
    UserService userService;
    TaskService taskService;

    @Autowired
    public ToDoServiceTest(ToDoService toDoService,
                            UserService userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }

    @Test
    public void getAllToDosByUserIdTest() {
        int expectedSize = 4;
        List<ToDo> toDos = toDoService.getByUserId(5L);
        assertEquals(expectedSize, toDos.size());
    }

    @Test
    @Transactional
    public void createToDoTest() {
        ToDo expected = new ToDo();
        User mike = userService.readById(4L);
        expected.setTitle("Mike's To-DO #5");
        expected.setOwner(mike);
        expected.setCreatedAt(LocalDateTime.now());

        ToDo actual = toDoService.create(expected);
        List<ToDo> list = toDoService.getAll();
        assertEquals(8, list.size());
    }

    @Test
    @Transactional
    public void readByIdTest() {
        assertEquals("Mike's To-Do #1", toDoService.readById(7).getTitle());
    }

    @Test
    @Transactional
    public void updateToDoTest(){
        ToDo expected = toDoService.readById(13);
        User user = userService.readById(4);
        expected.setTitle("New Title");
        expected.setOwner(user);

        ToDo actual = toDoService.update(expected);

        assertEquals(expected,actual);
    }

    @Test
    @Transactional
    public void deleteTodoTest() {

        toDoService.delete(13);
        toDoService.delete(11);
        List<ToDo> toDos = toDoService.getAll();
        assertEquals(5, toDos.size());
    }

    @Test
    public void exceptionWhenReadByNoneExists() {
        long id = -1L;
        assertThrows(EntityNotFoundException.class, () -> toDoService.readById(id));


    }

    @Test
    @Transactional
    public void getAllToDos(){
        toDoService.delete(13);
        toDoService.delete(12);
        toDoService.delete(11);
        toDoService.delete(10);
        toDoService.delete(9);
        toDoService.delete(8);
        toDoService.delete(7);

        List<ToDo> toDos = toDoService.getAll();
        assertEquals(0,toDos.size());
    }

}
