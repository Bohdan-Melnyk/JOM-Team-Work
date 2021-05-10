package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;

@Service
public class TaskServiceImpl implements TaskService {

	private ToDoService toDoService;

	@Autowired
	public TaskServiceImpl(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	public Task addTask(Task task, ToDo todo) {
		for (ToDo toDoList : toDoService.getAll()) {
            if(toDoList == todo) {
                if(toDoList.getTasks().add(task)){
                } else
                    throw new RuntimeException("User with this parapeters exist!");
            }
        }
        return task;
	}

	public Task updateTask(Task task) {
		Task taskResult = null;
        for (ToDo toDoList : toDoService.getAll()) {
            for (int i = 0; i < toDoList.getTasks().size(); i++) {
                if (toDoList.getTasks().get(i).getTaskId() == task.getTaskId()) {
                    toDoList.getTasks().get(i).setPriority(task.getPriority());
                    toDoList.getTasks().get(i).setName(task.getName());
                    taskResult = toDoList.getTasks().get(i);
                } else {
                    throw new RuntimeException("User not found!");
                }
            }
        }
        return taskResult;
	}

	public void deleteTask(Task task) {
		for (ToDo tasks: toDoService.getAll()){
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                if(tasks.getTasks().get(i).getTaskId() == task.getTaskId()) {
                    tasks.getTasks().remove(i);
                }
            }
        }
	}

	public List<Task> getAll() {
		List<Task> taskList = new ArrayList<>();
        for (ToDo toDoList : toDoService.getAll()){
            taskList.add((Task) toDoList.getTasks());
        }
        return taskList;
	}

	public List<Task> getByToDo(ToDo todo) {
		List<Task> taskListResult = null;
        for (ToDo toDoList: toDoService.getAll()) {
            if(toDoList == todo)
                taskListResult = toDoList.getTasks();
            else
                throw new RuntimeException("The ToDo: " + todo + " was not found!");
        }
        return taskListResult;
	}

	public Task getByToDoName(ToDo todo, String name) {
		 Task taskResult = null;
	        for (ToDo toDoList: toDoService.getAll()) {
	            if (toDoList == todo){
	                for (int i = 0; i < toDoList.getTasks().size(); i++) {
	                    if(toDoList.getTasks().get(i).getName() == name)
	                        taskResult = toDoList.getTasks().get(i);
	                    else
	                        throw new RuntimeException("Task with name: " + name + " was not found!");
	                }
	            } else {
	                throw new RuntimeException("ToDo list:  " + todo + " does not exists!");
	            }
	        }
	        return taskResult;
	}

	public Task getByUserName(User user, String name) {
		Task taskResult = null;
        for (ToDo toDoList: toDoService.getAll()) {
            if(toDoList.getOwner() == user) {
                for (int i = 0; i < toDoList.getTasks().size(); i++) {
                    if(toDoList.getTasks().get(i).getName() == name)
                        taskResult = toDoList.getTasks().get(i);
                    else
                        throw new RuntimeException("Task with name: " + name + " was not found!");
                }
            } else {
                throw new RuntimeException("User: " + user + " was not found!");
            }
        }
        return taskResult;
	}

}
