package com.softserve.itacademy.Controller;

import com.softserve.itacademy.dto.TaskDto;
import com.softserve.itacademy.dto.TaskTransformer;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;

import com.softserve.itacademy.service.StateService;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TaskIntegrationTest  {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskService taskService;
    
    @Autowired
    ToDoService toDoService;
    
    @Autowired
    StateService stateService;

    @Test
    void createTaskTest() throws Exception {
        long todoId = 7L;
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks/create/todos" + todoId)
                .flashAttr("name", "MyToDo"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void shouldUpdateTaskPostException() throws Exception {
        long todoId = 7L;
        long taskId = 6L;
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskId);
        taskDto.setName(" ");
        taskDto.setTodoId(todoId);
        taskDto.setStateId(6L);
        taskDto.setPriority(Priority.LOW.name());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks/" + taskId + "/update/todos/" + todoId).flashAttr("task", taskDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("priorities", Priority.values()))
                .andExpect(MockMvcResultMatchers.view().name("update-task"));
    }

    @Test
    void  createTaskGetTest() throws Exception {
        long todoId = 7L;
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/create/todos/" + todoId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("task"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("todo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("priorities"));

    }

    @Test
    void shouldUpdateTaskPost() throws Exception {
        long todoId = 7L;
        long taskId = 6L;
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskId);
        taskDto.setName("nameEdited");
        taskDto.setTodoId(todoId);
        taskDto.setStateId(6L);
        taskDto.setPriority(Priority.LOW.name());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks/" + taskId + "/update/todos/" + todoId).flashAttr("task", taskDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/todos/" + todoId + "/tasks"));

        Task actual = taskService.readById(taskId);
        assertEquals(taskDto.getName(), actual.getName());
        assertEquals(taskDto.getStateId(), actual.getState().getId());
        assertEquals(taskDto.getPriority(), actual.getPriority().name());
    }

    @Test
    void  updateTaskGetTest() throws Exception {
        long todoId = 7L;
        long taskId = 6L;
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/" + taskId + "/update/todos/" + todoId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("task"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("priorities"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("states"));
    }

    @Test
    void shouldCreateTaskPost() throws Exception {
        long todoId = 7L;

        TaskDto taskDto = new TaskDto();
        taskDto.setName("name");
        taskDto.setTodoId(todoId);
        taskDto.setPriority(Priority.LOW.name());

        Task task = TaskTransformer.convertToEntity(taskDto, todoService.readById(taskDto.getTodoId()), stateService.getByName("New"));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks/create/todos/" + todoId)
                        .flashAttr("task", taskDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/todos/" + todoId + "/tasks"));

        task.setId(1L);
        List<Task> actualList = taskService.getByTodoId(todoId);
        assertTrue(actualList.contains(task));
    }

    @Test
    void  deleteTaskGetTest() throws Exception {
        long todoId = 7L;
        long taskId = 6L;
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/" + taskId + "/delete/todos/" + todoId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
