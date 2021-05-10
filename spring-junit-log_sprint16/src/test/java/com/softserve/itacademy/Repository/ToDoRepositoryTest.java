package com.softserve.itacademy;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ToDoRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    RoleRepository roleRepository;


    @Test
    public void createToDoTest() {
        User user = userRepository.getOne(4l);
        ToDo toDo = new ToDo();
        toDo.setTitle("NewToDo");
        toDo.setCreatedAt(LocalDateTime.now());
        toDo.setOwner(user);
        toDoRepository.save(toDo);
        List<ToDo> list = toDoRepository.findAll();
        Assertions.assertEquals(8, list.size());
    }

    @Test
    public void deleteToDoTest() {
        ToDo toDo = toDoRepository.getOne(10l);
        toDoRepository.delete(toDo);
        List<ToDo> list = toDoRepository.findAll();
        Assertions.assertEquals(6, list.size());
    }

    @Test
    public void getToDoByUserIDTest() {
        List<ToDo> list = toDoRepository.getByUserId(6l);
        Assertions.assertEquals(4, list.size());
    }
}
