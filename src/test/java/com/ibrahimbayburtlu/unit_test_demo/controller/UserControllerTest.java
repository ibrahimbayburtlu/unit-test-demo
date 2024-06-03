package com.ibrahimbayburtlu.unit_test_demo.controller;

import com.ibrahimbayburtlu.unit_test_demo.Entity.User;
import com.ibrahimbayburtlu.unit_test_demo.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        User user = new User(1L, "testuser", "testuser@example.com");
        Mockito.when(userService.getUser(1L)).thenReturn(user);

        Mockito.doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            // addUser logic here if needed
            return null; // void method
        }).when(userService).addUser(1L, "testuser", "testuser@example.com");

        Mockito.doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            // updateUser logic here if needed
            return null; // void method
        }).when(userService).updateUser(1L, "updateduser", "updateduser@example.com");

        Mockito.doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            // deleteUser logic here if needed
            return null; // void method
        }).when(userService).deleteUser(1L);
    }

    @Test
    @DisplayName("Test Add User")
    public void testAddUser() throws Exception {
        mockMvc.perform(post("/user/add")
                        .param("id", "1")
                        .param("username", "testuser")
                        .param("email", "testuser@example.com"))
                .andExpect(status().isOk());

        Mockito.verify(userService).addUser(1L, "testuser", "testuser@example.com");
    }

    @Test
    @DisplayName("Test Get User")
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    @DisplayName("Test Update User")
    public void testUpdateUser() throws Exception {
        mockMvc.perform(put("/user/1")
                        .param("newUsername", "updateduser")
                        .param("newEmail", "updateduser@example.com"))
                .andExpect(status().isOk());

        Mockito.verify(userService).updateUser(1L, "updateduser", "updateduser@example.com");
    }

    @Test
    @DisplayName("Test Delete User")
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk());

        Mockito.verify(userService).deleteUser(1L);
    }
}
