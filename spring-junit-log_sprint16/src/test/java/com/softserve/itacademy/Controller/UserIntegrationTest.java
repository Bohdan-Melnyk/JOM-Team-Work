package com.softserve.itacademy.Controller;

import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    public void createUser() throws  Exception{
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("firstName", "Sam");
        params.add("lastName", "Evans");
        params.add("password","1111");
        params.add("email", "sam@mail.com");
        mockMvc.perform(MockMvcRequestBuilders.get("/users/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create").params(params))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void updateUser() throws Exception{
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("firstName", "Nora");
        params.add("lastName", "Evans");
        params.add("password","3333");
        params.add("email", "nora@mail.com");
        params.add("roleId", "1");
        mockMvc.perform(MockMvcRequestBuilders.get("/users/6/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/6/update").params(params))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

    }


    @Test
    public void readValidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/4/read"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    public void readInvalidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/10/read"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteValidUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/users/5/read"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/5/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/5/read"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void deleteInvalidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/10/delete"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
