package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    ToDoRepository toDoRepository;

    @Autowired
    public void setToDoRepository(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    @Override
    public ToDo create(ToDo todo) {
        return toDoRepository.saveAndFlush(todo);
    }

    @Override
    public ToDo readById(long id) {
        return toDoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Todo with id " + id + " does not exists"));
    }

    @Override
    public ToDo update(ToDo todo) {
        return toDoRepository.saveAndFlush(todo);
    }

    @Override
    public void delete(long id) {
        if (!toDoRepository.existsById(id)) {
            throw new IllegalArgumentException("Todo with id " + id + " does not exists");
        }
        toDoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        return toDoRepository.findByOwnerId(userId);
    }
}
