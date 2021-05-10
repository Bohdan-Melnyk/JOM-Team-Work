package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    UserService userService;

    @Autowired
    ToDoService toDoService;

    @GetMapping("/create/users/{owner_id}")
    public String create(@PathVariable("owner_id") Long ownerId, Model model) {
        model.addAttribute("todo", new ToDo());
        return "create-todo";
    }

    @PostMapping("/create/users/{owner_id}")
    public String create(@ModelAttribute("todo") ToDo toDo, @PathVariable("owner_id") Long ownerId) {
        toDo.setOwner(userService.readById(ownerId));
        toDoService.create(toDo);
        return "redirect:/todos/all/users/{owner_id}";
    }

    @GetMapping("/{id}/tasks")
    public String read(@PathVariable(name = "id") Integer id, Model model) {
        model.addAttribute("todo", toDoService.readById(id));
        return "todo-tasks";
    }

    @GetMapping("/{todo_id}/update/users/{owner_id}")
    public String update(@PathVariable("todo_id") Long todoId, @PathVariable("owner_id") Long ownerId, Model model) {

        model.addAttribute("toDo", toDoService.readById(todoId));
        return "update-todo";
    }

    @PostMapping("/{todo_id}/update/users/{owner_id}")
    public String update(@PathVariable("todo_id") Long todoId, @PathVariable("owner_id") Long ownerId,
                         @ModelAttribute("toDo") ToDo toDo) {
        toDoService.update(toDo);
        return "redirect:/todos/all/users/{owner_id}";
    }

    @GetMapping("/{todo_id}/delete/users/{owner_id}")
    public String delete(@PathVariable("todo_id") Long todoId, @PathVariable("owner_id") Long ownerId) {
        toDoService.delete(ownerId);
        return "redirect:/todos/all/users/{owner_id}";
    }

    @GetMapping("/all/users/{user_id}")
    public String getAll(@PathVariable("user_id") Long userId, Model model) {
        model.addAttribute("todos", toDoService.getByUserId(userId));
        return "todo-lists";
    }

    @GetMapping("/{id}/add")
    public String addCollaborator(@PathVariable("id") Long id, Model model) {
        toDoService.readById(id);
        return "redirect:/todos/{id}/tasks";
    }

    @GetMapping("/{id}/remove")
    public String removeCollaborator(@PathVariable("id") Long id) {
        toDoService.delete(id);
        return "redirect:/todos/{id}/tasks";
    }
}
