package com.softserve.itacademy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

@RunWith(JUnitPlatform.class)
public class UserServiceTest {
    private static UserService userService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        annotationConfigContext.close();
    }

    @Test
    public void checkAddUser() {
    	List<ToDo> todo = new ArrayList<>();
        User user = new User(1, "FirstName", "LastName", "email", "password", todo);       // TODO, update code
        User expected = user;   // TODO, update code
        User actual = userService.addUser(user);
        Assertions.assertEquals(expected, actual, "check message");
    }
    
    @Test
    public void updateUser() {
    	List<ToDo> todo = new ArrayList<>();
        User user = new User(1, "FirstName", "LastName", "email", "password", todo);
        User expected = user;   // TODO, update code
        User actual = userService.updateUser(user);
        Assertions.assertEquals(expected, actual, "check message");
    }
    
}
