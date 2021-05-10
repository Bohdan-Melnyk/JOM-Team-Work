package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskServiceTest {

    TaskService taskService;

    @Autowired
    public TaskServiceTest(TaskService taskService){
        this.taskService = taskService;
    }


    @Test
    @Transactional
    public void createTaskTest(){
        Task task = new Task();
        task.setName("NotNew");
        task = taskService.create(task);
        assertNotEquals(0, task.getId());
    }
    @Test
    @Transactional
    public void getAllTaskTest(){
        int expectedSize = 3;
        List<Task> states = taskService.getAll();
        assertEquals(expectedSize, states.size());
    }

    @Test
    @Transactional
    public void updateTaskTest(){
        String newName = "Task #new";
        Task task = taskService.readById(5L);
        task.setName(newName);
        Task actual = taskService.update(task);
        Task expected = new Task();
        expected = taskService.readById(5l);
        expected.setName(newName);
        assertEquals(expected, actual);
    }

    @Test
    public void getTaskByToDoId(){
        int expected = 3;
        List<Task> tasks = taskService.getByTodoId(7L);
        int actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void deleteTaskTest(){
        taskService.delete(6L);
        taskService.delete(5L);
        List<Task> users = taskService.getAll();
        assertEquals(1, users.size());
    }

}
