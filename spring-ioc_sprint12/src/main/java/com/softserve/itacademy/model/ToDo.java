package com.softserve.itacademy.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ToDo {

    private int toDoId;

    private String title;

    private LocalDateTime createdAt;

    private User owner;

    private List<Task> tasks;

    public ToDo() {
    }

    public ToDo(int toDoId, String title, LocalDateTime createdAt, User owner, List<Task> tasks) {
        this.toDoId = toDoId;
        this.title = title;
        this.createdAt = createdAt;
        this.owner = owner;
        this.tasks = tasks;
    }

    public int getToDoId() {
        return toDoId;
    }

    public void setToDoId(int toDoId) {
        this.toDoId = toDoId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo)) return false;
        ToDo toDo = (ToDo) o;
        return getToDoId() == toDo.getToDoId() && getTitle().equals(toDo.getTitle()) && getCreatedAt().equals(toDo.getCreatedAt()) && getOwner().equals(toDo.getOwner()) && getTasks().equals(toDo.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToDoId(), getTitle(), getCreatedAt(), getOwner(), getTasks());
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "toDoId=" + toDoId +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", owner=" + owner +
                ", tasks=" + tasks +
                '}';
    }
}
