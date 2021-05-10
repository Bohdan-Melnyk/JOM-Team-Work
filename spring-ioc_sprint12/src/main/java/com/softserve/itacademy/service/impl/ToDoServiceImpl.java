package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

@Service
public class ToDoServiceImpl implements ToDoService {

    private UserService userService;

    @Autowired
    public ToDoServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<Integer> get(int toDoId) {
        return Optional.of(toDoId);
    }


    public ToDo addTodo(ToDo todo, User user) {

        if ( !userService.getAll().stream().anyMatch(u->u.getUserId() == user.getUserId())){
                return todo;
            }
        return null;
    }

    public ToDo updateTodo(ToDo todo) {

        List<ToDo> myTodos = todo.getOwner().getMyTodos();
        for (int i = 0, myTodosSize = myTodos.size(); i < myTodosSize; i++) {
            ToDo toDo = myTodos.get(i);
            if (toDo.getToDoId() == todo.getToDoId()) {
                toDo.setTitle(todo.getTitle());
                toDo.setOwner(todo.getOwner());
                toDo.setTasks(todo.getTasks());
                toDo.setCreatedAt(todo.getCreatedAt());
                return toDo;
            }
        }
        return null;
    }

    public void deleteTodo(ToDo todo) {
        todo.getOwner().getMyTodos().remove(todo);
    }

    public List<ToDo> getAll() {
        List<ToDo> list = new ArrayList<>();
        userService.getAll().stream().map(User::getMyTodos).forEach(list::addAll);
        return list;
    }

    public List<ToDo> getByUser(User user) {
        return user.getMyTodos();
    }

    public ToDo getByUserTitle(User user, String title) {
        return user.getMyTodos().stream().filter(task -> task.getTitle().equals(title)).findFirst().orElse(null);
    }
}
