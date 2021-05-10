package com.softserve.itacademy;


import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void newRoleTest() {
        Role exspected = new Role();
        exspected.setName("NEWROLE");
        roleRepository.save(exspected);
        List<Role> list = roleRepository.findAll();
        int actual = list.size();
        Assertions.assertEquals(3, actual);
    }
}
