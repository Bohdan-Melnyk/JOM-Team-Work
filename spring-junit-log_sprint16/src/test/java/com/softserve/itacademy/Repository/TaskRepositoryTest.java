package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.repository.ToDoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    ToDoRepository toDoRepository;

    @Test
    public void addTaskTest() {
        Task task = new Task();
        State state = stateRepository.getByName("New");
        ToDo toDo = toDoRepository.getOne(7l);
        task.setName("Task #4");
        task.setPriority(Priority.HIGH);
        task.setState(state);
        task.setTodo(toDo);
        taskRepository.save(task);
        List<Task> list = taskRepository.findAll();
        Assertions.assertEquals(4, list.size());
    }

    @Test
    public void deleteTaskTest() {
        Task task = taskRepository.getOne(5l);
        taskRepository.delete(task);
        List<Task> list = taskRepository.findAll();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void getTaskByToDoIdTest() {
        List<Task> tasks = taskRepository.getByTodoId(7l);
        Assertions.assertEquals(3, tasks.size());
    }

}
