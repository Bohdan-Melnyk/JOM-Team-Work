package com.softserve.itacademy.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class ToDoTests {

    private static ToDo toDo;

    @BeforeAll
    static void init(){
        toDo = new ToDo();
        toDo.setTitle("ToDoWorkProject");
    }

    @Test
    public void toDoWithValidTitle(){
        ToDo toDo = new ToDo();
        User user = new User();
        Role role = new Role();
        Task task = new Task();
        State state = new State();
        state.setName("Start");
        role.setName("Admin");
        user.setFirstName("Orest");
        user.setEmail("orestIt@icloud.com");
        user.setPassword("SyperPasswd");
        user.setRole(role);
        toDo.setOwner(user);
        toDo.setTitle("SchoolToDo");
        toDo.setTaskList(Arrays.asList(task));
        task.setState(state);
        user.setMyTodos(Arrays.asList(toDo));


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);

        Assertions.assertEquals(0,violations.size());
    }

    @Test
    public void toDoWithNotValidTitle(){
        ToDo toDo = new ToDo();
        User user = new User();
        Role role = new Role();
        role.setName("Admin");
        user.setFirstName("Orest");
        user.setEmail("orestIt@icloud.com");
        user.setPassword("SyperPasswd");
        user.setRole(role);
        toDo.setOwner(user);
        toDo.setTitle(" ");


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);

        Assertions.assertEquals(1,violations.size());
    }

}
