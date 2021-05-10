package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/read-task")
public class ReadTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isExist = taskRepository.all().stream().anyMatch(task -> task.getId() == id);
        Task task = taskRepository.read(id);
        if (isExist) {
            request.setAttribute("task", task);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/read-task.jsp");
            requestDispatcher.forward(request, response);

        } else {
            response.setStatus(404);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }

    }
}