package com.softserve.itacademy.service;

import com.softserve.itacademy.model.*;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskServiceTest {

    private TaskService taskService;
    private Task task;

    @Autowired
    public TaskServiceTest(TaskService taskService) {
        this.taskService = taskService;
    }

    @Test
    public void getAllTasksTest() {
        int expectedSize = 3;
        List<Task> tasks = taskService.getAll();
        assertTrue(expectedSize <= tasks.size(), String.format("At least %d tasks should be in tasks table", expectedSize));
    }

    @Test
    public void createTask() {
        task = new Task();
        task.setName("Task #5");
        task.setPriority(Priority.MEDIUM);
        task.setId(1L);
        long id = taskService.create(task).getId();
        Assertions.assertTrue(taskService.readById(id)!=null);
    }

    @Test
    public void createIncorrectTask() {
        Assertions.assertThrows(NoSuchElementException.class, () -> taskService.create(taskService.readById(23L)));
    }

    @Test
    public void getTaskByID() {
        int id = 6;
        Task task = taskService.readById(id);
        Assertions.assertEquals(task.getId(), id);
    }

    @Test
    public void getTaskByIncorrectID() {
        int id = 20;
        Assertions.assertThrows(NoSuchElementException.class, () -> taskService.readById(id));
    }

    @Test
    public void updateTask() {
        Task task = taskService.readById(7L);
        String newName="Task #4";
        task.setName(newName);
        task.setPriority(Priority.HIGH);
        task.setState(task.getState());
        task.setTodo(task.getTodo());
        taskService.update(task);
        Task updatedTask = taskService.readById(7L);

        Task expected=new Task();
        expected.setName("Task #4");
        expected.setPriority(Priority.HIGH);
        expected.setId(7);
        expected.setState(task.getState());
        expected.setTodo(task.getTodo());
        Assertions.assertEquals(expected.toString(), updatedTask.toString());
    }

    @Test
    public void updateIncorrectTask(){
        Assertions.assertThrows(NoSuchElementException.class, ()-> taskService.update(taskService.readById(50)));
    }

    @Test
    public void deleteTask() {
        taskService.delete(6L);
        Assertions.assertThrows(NoSuchElementException.class, () -> taskService.readById(1));
    }

    @Test
    public void getTaskByTodoId() {
        taskService.getAll();
        task = taskService.readById(7);
        Assertions.assertEquals(task.getTodo().getId(), taskService.getByTodoId(7).stream().findFirst().get().getTodo().getId());
    }

    @Test
    public void getTaskByIncorrectTodoId() {
        Assertions.assertEquals(0,taskService.getByTodoId(100).size());
    }
}
