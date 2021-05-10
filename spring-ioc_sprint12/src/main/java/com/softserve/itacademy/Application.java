package com.softserve.itacademy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

public class Application {

	public static void main(String[] args) {
        try (AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class)) {
            UserService userService = annotationConfigContext.getBean(UserService.class);
            TaskService taskService = annotationConfigContext.getBean(TaskService.class);
            ToDoService toDoService = annotationConfigContext.getBean(ToDoService.class);
            

            System.out.println(userService);
            System.out.println(taskService);
            System.out.println(toDoService);
        }
    }

}
