package com.softserve.itacademy.model;

import java.util.Objects;

public class Task {
    private int taskId;
    
    

    private String name;

    private Priority priority;

    public Task() {
    }

    public Task(int taskId, String name, Priority priority) {
        this.taskId = taskId;
        this.name = name;
        this.priority = priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTaskId() == task.getTaskId() && getName().equals(task.getName()) && getPriority() == task.getPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getName(), getPriority());
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
