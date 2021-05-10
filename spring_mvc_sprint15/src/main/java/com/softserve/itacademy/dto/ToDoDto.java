package com.softserve.itacademy.dto;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class ToDoDto {
    private long id;
    private String title;
    private LocalDateTime createdAt;
    private User owner;
    private List<Task> tasks;
    private List<User> collaborators;

    public ToDoDto() {
    }

    public ToDoDto(long id, String title, LocalDateTime createdAt, User owner, List<Task> tasks, List<User> collaborators) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.owner = owner;
        this.tasks = tasks;
        this.collaborators = collaborators;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
    }
}
