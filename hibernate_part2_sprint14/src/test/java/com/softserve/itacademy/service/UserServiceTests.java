package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTests {

    UserService userService;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void getAllUsersTest() {
        int expectedSize = 3;
        List<User> users = userService.getAll();
        assertTrue(expectedSize <= users.size(), String.format("At least %d users shuold be in users table", expectedSize));
    }

    @Test
    @Transactional
    public void addUserTest(){
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setLastName("Oleg");
        user.setFirstName("Romanov");
        user.setPassword("$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX");
        user.setEmail("lol@gmail.com");
        users.add(user);
        Role role = new Role();
        user.setRole(role);
        role.setUsers(users);
        userService.create(user);
        User actual = userService.readById(user.getId());
        assertEquals(user, actual);
    }

    @Test
    @Transactional
    public void getUserByEmail() {
        User expected = userService.readById(5L);
        User actual = userService.getByEmail("nick@mail.com");
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void deleteTaskTest(){
        userService.delete(6L);
        userService.delete(5L);
        List<User> users = userService.getAll();
        assertEquals(1, users.size());
    }
    @Test
    @Transactional
    public void updateTest(){
        User expected = userService.readById(5);
        expected.setFirstName("Dima");
        expected.setLastName("Kopelchak");
        userService.update(expected);
        User actual = userService.readById(5);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void readByIdTest(){
        User user = userService.getByEmail("nick@mail.com");
        User actual = userService.readById(user.getId());
        assertEquals(user, actual);
    }
}