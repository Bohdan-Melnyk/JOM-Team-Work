package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception_handling.EntityNotFoundException;
import com.softserve.itacademy.exception_handling.NullEntityReferenceException;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository todoRepository;

    public ToDoServiceImpl(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo create(ToDo todo) throws NullEntityReferenceException {
        if (todo == null) {
            throw new NullEntityReferenceException("ToDo can't be null");
        }
        return todoRepository.save(todo);
    }

    @Override
    public ToDo readById(long id) throws EntityNotFoundException {
        Optional<ToDo> optional = todoRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("There is no toDo with ID " + id + " in database"));

    }

    @Override
    public ToDo update(ToDo todo) throws EntityNotFoundException, NullEntityReferenceException {
        if (todo == null) {
            throw new NullEntityReferenceException("ToDo can't be 'null'!");
        }
        readById(todo.getId());
        return todoRepository.save(todo);
    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
        ToDo todo = readById(id);
        todoRepository.delete(todo);
    }

    @Override
    public List<ToDo> getAll() {
        List<ToDo> todos = todoRepository.findAll();
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        List<ToDo> todos = todoRepository.getByUserId(userId);
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }
}
