package com.softserve.itacademy.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @Column
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "todo_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "20"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @NotBlank(message = "The title cannon be empty")
    @Column(nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "toDo")
    private List<Task> taskList;

    @ManyToMany(mappedBy = "toDos")
    List<User> userList;

    public long getId() {
        return id;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", title='" + title + '\'' +
                '}';
    }
}
