package com.softserve.itacademy.dto;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;

public class ToDoTransformer {
    public static ToDoDto convertToDto(ToDo toDo) {
        return new ToDoDto(
                toDo.getId(),
                toDo.getTitle(),
                toDo.getCreatedAt(),
                toDo.getOwner(),
                toDo.getTasks(),
                toDo.getCollaborators()
        );
    }
}
