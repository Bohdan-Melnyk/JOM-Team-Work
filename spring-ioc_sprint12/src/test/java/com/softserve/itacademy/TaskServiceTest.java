package com.softserve.itacademy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

@RunWith(JUnitPlatform.class)
public class TaskServiceTest {
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
    
    
    
    @Test()
    public void checkAddTask() {
        ToDo todo = new ToDo();

        Task expected = new Task(1, "name1", Priority.LOW);

        Task actual = taskService.addTask(expected, todo);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void checkUpdateTask() {

        Task task1 = new Task(1, "Go to shop", Priority.MEDIUM);
        Task task2 = new Task(2, "Go to office", Priority.HIGH);

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        User owner = new User(1, "Orest", "IT", "someEmail@dot.com", "passwd", new ArrayList<>());

        ToDo toDo = new ToDo(1, "MyTitle2", LocalDateTime.now(), owner, taskList);


        userService.addUser(owner);
        toDoService.addTodo(toDo, owner);

        List<Task> taskListToDo = toDo.getTasks();
        taskListToDo.add(task1);
        taskListToDo.add(task2);

        Task expected = new Task(1, "name4", Priority.HIGH);
        Task actual = taskService.updateTask(expected);

        Assertions.assertEquals(expected, actual);

    }
@Test
    public void checkGetAll() {

        User user = new User(1,"Ivan", "Ivanenko", "ivan@gmail.com","123",  new ArrayList<>());
        ToDo toDo = new ToDo(1, "MyTitle2", LocalDateTime.now(), user, new ArrayList<>());
        Task taskOne = new Task(1,"checkAllTask", Priority.HIGH);
        Task taskTwo = new Task(2,"TestMethods", Priority.LOW);
        Task taskThree = new Task(3,"CreateMethods", Priority.MEDIUM);
        taskService.addTask(taskOne, toDo);
        taskService.addTask(taskTwo, toDo);
        taskService.addTask(taskThree, toDo);
        List<Task> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);

        Assertions.assertEquals(taskList, taskService.getAll());
    }
}
