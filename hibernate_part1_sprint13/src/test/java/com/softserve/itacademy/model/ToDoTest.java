package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoTest {
    private static Role role;
    private static User user;
    private static ToDo toDo;

    @BeforeAll
    static void init() {
        role = new Role();
        user = new User();
        toDo = new ToDo();
        user.setRole(role);
        toDo.setTitle("Title");
        toDo.setCreatedAt(LocalDateTime.now());
        toDo.setOwner(user);
    }

    @Test
    void todoWithValidTitle() {
        ToDo todo = new ToDo();
        todo.setOwner(user);
        todo.setTitle("Title");
        todo.setCreatedAt(LocalDateTime.now());



        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todo);

        assertEquals(0, violations.size());
    }

    @Test
    void createValidTodo() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTitleToDo")
    void constraintViolationInvalidTitle(String input, String errorValue) {
        ToDo toDo = new ToDo();
        toDo.setOwner(toDo.getOwner());
        toDo.setTitle(input);
        toDo.setCreatedAt(toDo.getCreatedAt());


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidTitleToDo(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid")
        );
    }

}
