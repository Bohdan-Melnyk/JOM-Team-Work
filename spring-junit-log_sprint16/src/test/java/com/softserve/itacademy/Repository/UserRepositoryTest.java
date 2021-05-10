package com.softserve.itacademy.Repository;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void getUserByEmail() {
        User user = new User();
        Role role = roleRepository.getOne(1L);
        user.setRole(role);
        user.setFirstName("Elle");
        user.setLastName("Fann");
        user.setPassword("1111");
        user.setEmail("elle@mail.com");
        user.setOtherTodos(new ArrayList<ToDo>());
        user.setMyTodos(new ArrayList<ToDo>());
        User actual = userRepository.save(user);
        Assertions.assertTrue(userRepository.getUserByEmail("elle@mail.com")==actual);
    }

}
