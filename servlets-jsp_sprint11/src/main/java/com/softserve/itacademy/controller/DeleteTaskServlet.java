package com.softserve.itacademy.controller;

import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-task")
public class DeleteTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isExist = taskRepository.all().stream().anyMatch(task -> task.getId() == id);
        taskRepository.delete(id);
        if (isExist) {
            response.sendRedirect("tasks-list");
        } else {
            response.setStatus(404);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }


    }


}
