package com.softserve.itacademy.model;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TaskTest1 {

    private static Task task;



    @Test
    void createValidTask() {
        Role role = new Role();
        User user = new User();
        task = new Task();
        State state = new State();
        ToDo toDo = new ToDo();
        role.setName("Role");
        user.setRole(role);
        user.setEmail("email");
        state.setName("Name");
        toDo.setOwner(user);
        toDo.setTitle("Title");
        toDo.setCreatedAt(LocalDateTime.now());
        task.setName("taskName");
        task.setState(state);
        task.setToDo(toDo);
        task.setPriority(Priority.HIGH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(0, violations.size());
    }

    @Test
    void TaskWithValidState() {
        Role role = new Role();
        User user = new User();
        task = new Task();
        State state = new State();
        ToDo toDo = new ToDo();
        role.setName("Role");
        user.setRole(role);
        user.setEmail("email");
        state.setName("Name");
        toDo.setOwner(user);
        toDo.setTitle("Title");
        toDo.setCreatedAt(LocalDateTime.now());
        task.setName("taskName");
        task.setState(state);
        task.setToDo(toDo);
        task.setPriority(Priority.HIGH);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(0, violations.size());
    }

    @Test
    void TaskWithInValidState() {
        Role role = new Role();
        User user = new User();
        task = new Task();
        State state = new State();
        ToDo toDo = new ToDo();
        role.setName("Role");
        user.setRole(role);
        user.setEmail("email");
        state.setName("Name");
        toDo.setOwner(user);
        toDo.setTitle("Title");
        toDo.setCreatedAt(LocalDateTime.now());
        task.setName(" ");
        task.setState(state);
        task.setToDo(toDo);
        task.setPriority(Priority.HIGH);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(1, violations.size());
    }
}
