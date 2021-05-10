package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.RoleService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAll());
        return "create-user";
    }


    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/todos/all/users/" + user.getId();
    }


    @GetMapping("/{id}/read")
    public String read(@PathVariable(name = "id") Integer id, Model model) {
        model.addAttribute("user", userService.readById(id));
        return "user-info";

    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable(name = "id") Integer id, Model model) {
        model.addAttribute("user", userService.readById(id));
        model.addAttribute("roles", roleService.getAll());
        return "update-user";
    }

    @PostMapping("/{id}/update")
    public String update(@Valid @ModelAttribute User user, @RequestParam(name = "oldPassword") String oldPassword) {
        if (user.getPassword().isEmpty() || user.getPassword() == null) {
            user.setPassword(oldPassword);
        }
        userService.update(user);
        return "redirect:/home";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id) {
        userService.delete((long) id);
        return "redirect:/home";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }
}
