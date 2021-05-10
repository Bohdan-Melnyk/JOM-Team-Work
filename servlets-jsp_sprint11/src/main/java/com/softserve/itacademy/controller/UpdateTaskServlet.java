package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-task")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private Task task;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isExist = taskRepository.all().stream().anyMatch(task -> task.getId() == id);
        task = taskRepository.read(id);
        if (isExist) {
            request.setAttribute("task", task);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.setStatus(404);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        task.setTitle(request.getParameter("title"));
        String priority = request.getParameter("priority").toUpperCase();
        if (priority.isEmpty()) {
            request.setAttribute("error", "Expected value in drop-down list but it was empty!");
            request.getRequestDispatcher("WEB-INF/pages/create-task.jsp").forward(request, response);
        }
        task.setPriority(Priority.valueOf(priority.toUpperCase()));
        if (taskRepository.update(task)) {
            response.setStatus(200);
            response.sendRedirect("/tasks-list");
        }
    }
}
