package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class RoleServiceTests {
    RoleService roleService;

    @Autowired
    public RoleServiceTests(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test

    public void getAllRolesTest() {
        int expectedSize = 2;
        List<Role> users = roleService.getAll();
        assertTrue(expectedSize <= users.size(), String.format("At least %d roles should be in roles table", expectedSize));
    }

    @Test
    @Transactional
    public void createRoleTest(){
        Role role = new Role();
        role.setName("NotNew");
        role = roleService.create(role);
        assertNotEquals(0, role.getId());
    }


    @Test
    @Transactional
    public void readRoleById() {
        Role expected = new Role();
        expected.setName("ADMIN");
        Role actual = roleService.readById(1L);
        assertEquals(expected.getName(), actual.getName());
    }

}
