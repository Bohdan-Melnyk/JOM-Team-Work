package com.softserve.itacademy;


import com.softserve.itacademy.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ToDoIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ToDoService toDoService;

    @Test
    void PostCreateTest() throws Exception {
        long ownerId = 6L;
        mockMvc.perform(MockMvcRequestBuilders.post("/todos/create/users/" + ownerId)
                .param("title", "Mike's"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

    }

    @Test
    void getAllTest() throws Exception {
        long userId = 6L;
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/all/users/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("todos"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    void GetReadTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/7/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("todo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tasks"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    void GetReadInvalidTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/6/tasks"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void GetUpdateTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/7/update/users/6"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("todo"));
    }

    @Test
    void GetDeleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/7/delete/users/6"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }


}
