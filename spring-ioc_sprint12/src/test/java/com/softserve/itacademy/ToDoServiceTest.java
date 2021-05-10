package com.softserve.itacademy;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class ToDoServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;
    private static TaskService taskService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        taskService = annotationConfigContext.getBean(TaskService.class);
        annotationConfigContext.close();
    }

    @Test
    public void checkAddToDo_shouldAdd() {
        userService.addUser(user1);
        userService.addUser(user3);
        ToDo testing = new ToDo(1, "TestTask", LocalDateTime.of(2011, 11, 6, 6, 30, 50, 100000), user1, new ArrayList<>());
        ToDo expected = new ToDo(1, "TestTask", LocalDateTime.of(2011, 11, 6, 6, 30, 50, 100000), user1, new ArrayList<>());
        ToDo testing1 = new ToDo(2, "TestTask", LocalDateTime.of(2012, 10, 6, 7, 35, 50, 100000), user3, new ArrayList<>());
        ToDo expected1 = new ToDo(2, "TestTask", LocalDateTime.of(2012, 10, 6, 7, 35, 50, 100000), user3, new ArrayList<>());
        ToDo actual = toDoService.addTodo(testing, user1);
        ToDo actual1 = toDoService.addTodo(testing1, user3);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected1, actual1);
    }

    @Test
    public void checkAddToDoExisting_shouldReturnNull() {
        userService.addUser(user4);
        //  userService.addUser(user4);
        //   userService.addUser(user4);
        ToDo testing = new ToDo(1, "TestTask", LocalDateTime.of(2011, 11, 6, 6, 30, 50, 100000), user4, new ArrayList<>());
        ToDo testing1 = new ToDo(1, "TestTask", LocalDateTime.of(2011, 11, 6, 6, 30, 50, 100000), user4, new ArrayList<>());
        toDoService.addTodo(testing, user1);
        ToDo actual = toDoService.addTodo(testing1, user1);
        Assertions.assertEquals(null, actual);
    }


    @Test
    public void checkGetAll() {
        userService.addUser(user2);
        ToDo testing = new ToDo(1, "TestTask", LocalDateTime.of(2011, 11, 6, 6, 30, 50, 100000), user2, new ArrayList<>());
        ToDo testing1 = new ToDo(1, "TestTask", LocalDateTime.of(2011, 11, 6, 6, 30, 50, 100000), user2, new ArrayList<>());
        toDoService.addTodo(testing1, user2);
        toDoService.addTodo(testing, user2);
        Assertions.assertEquals(2, toDoService.getAll().size());
    }


    @Test
    public void updateToDo_shouldUpdate() {

        userService.addUser(user1);
        List<Task> oldTasks = new ArrayList<>();
        List<Task> updatedTasks = new ArrayList<>();
        ToDo todo = new ToDo(2, "Abrakadabra", LocalDateTime.of(2012, 10, 6, 7, 35, 50, 100000), user1, oldTasks);
        toDoService.addTodo(todo, user1);
        ToDo newSomethingToDoing = new ToDo(2, "Abrakadabra", LocalDateTime.of(2012, 10, 6, 7, 35, 50, 100000), user1, updatedTasks);
        ToDo expected = new ToDo(2, "Abrakadabra", LocalDateTime.of(2012, 10, 6, 7, 35, 50, 100000), user1, updatedTasks);
        ToDo actual = toDoService.updateTodo(newSomethingToDoing);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deleteToDo_shouldDelete() {
        userService.addUser(user3);
        ToDo expected = new ToDo(2, "Abwgd", LocalDateTime.of(2011, 11, 6, 6, 30, 50, 100000), user3, new ArrayList<>());
        toDoService.addTodo(expected, user3);
        toDoService.deleteTodo(expected);
        Assertions.assertEquals(toDoService.getAll().size(), 0);
    }

    //testData:

    private List<ToDo> whatToDo = new ArrayList<>();
    private List<ToDo> whatToDo1 = new ArrayList<>();
    private User user1 = new User(1, "Misko", "Salamaga", "misco@gamil.com", "12345689", whatToDo);
    private User user2 = new User(2, "Valera", "Loris", "valera@mail.com", "987654", whatToDo);
    private User user3 = new User(3, "Petro", "Kenguru", "petro@yahoo.com", "0001112", whatToDo1);
    private User user4 = new User(4, "Grishka", "Lopez", "grisa@protonmail.com", "986321", whatToDo1);
}
