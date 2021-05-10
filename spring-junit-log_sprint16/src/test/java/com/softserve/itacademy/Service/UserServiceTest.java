package com.softserve.itacademy.Service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.RoleService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private User user;
    @Autowired
    private RoleService roleService;


    @Test
    public void createUser() {
        user = new User();
        Role role = roleService.readById(1L);
        role.setName("USER");
        user.setFirstName("Ashlyn");
        user.setLastName("Willson");
        user.setPassword("rabbit");
        user.setRole(role);
        user.setEmail("ashe@mail.com");
        user.setMyTodos(new ArrayList<ToDo>());
        user.setOtherTodos(new ArrayList<ToDo>());

        Assertions.assertTrue(userService.create(user) != null);
    }

    @Test
    public void readUser() {
        Assertions.assertEquals("nick@mail.com", userService.readById(5).getEmail());
    }

    @Test
    public void updateUser() {
        User toUpd = userService.readById(5);
        toUpd.setFirstName("Ernest");
        toUpd.setLastName("Hemingway");
        toUpd.setEmail("ernest@mail.com");
        User actual = userService.update(toUpd);
        Assertions.assertEquals("Ernest", actual.getFirstName());
        Assertions.assertEquals("Hemingway", actual.getLastName());
        Assertions.assertEquals("ernest@mail.com", actual.getEmail());
        Assertions.assertEquals(actual.getId(), 5L);
    }


    @Test
    public void deleteUser(){
        userService.delete(5L);
        Assertions.assertThrows(EntityNotFoundException.class, ()->userService.readById(5L));
    }

    @Test
    public void getAllUsers()
    {
        Assertions.assertTrue(userService.getAll().size()>=2);
    }


}
