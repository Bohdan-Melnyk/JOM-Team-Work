package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-task")
public class CreateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/create-task.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String title = request.getParameter("title");
        String priority = request.getParameter("priority");

        boolean isValidate = taskRepository.all().stream().anyMatch(task -> task.getTitle().equals(title));


        if (isValidate || title.isEmpty()) {
            if (!isValidate) {
                request.setAttribute("error", "Expected value in input field but it was empty!");
                request.getRequestDispatcher("WEB-INF/pages/create-task.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Task with a given name already exists!");
                request.getRequestDispatcher("WEB-INF/pages/create-task.jsp").forward(request, response);
            }
        } else {
            Priority prior = Priority.valueOf(priority.toUpperCase());
            boolean isCreated = taskRepository.create(new Task(title, prior));
            if (isCreated) {
                response.sendRedirect("/tasks-list");
            }
        }
    }
}
