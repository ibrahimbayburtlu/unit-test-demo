package com.ibrahimbayburtlu.unit_test_demo.service;

import com.ibrahimbayburtlu.unit_test_demo.Entity.User;
import com.ibrahimbayburtlu.unit_test_demo.Service.UserService;
import com.ibrahimbayburtlu.unit_test_demo.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test Add User")
    public void testAddUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("ibrahim");
        user.setEmail("ibrahim@example.com");

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.addUser(1L, "ibrahim", "ibrahim@example.com");

        assertEquals("ibrahim", savedUser.getUsername()); // True
        assertNotEquals("betül@example.com", savedUser.getEmail()); // True
    }

    @Test
    @DisplayName("Test Get User")
    public void testGetUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("ibrahim");
        user.setEmail("ibrahim@example.com");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(user));

        User retrievedUser = userService.getUser(1L);

        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
    }

    @Test
    @DisplayName("Test Update User")
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("ibrahimbayburtlu");
        existingUser.setEmail("ibrahimbayburtlu@example.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("updateduser");
        updatedUser.setEmail("updateduser@example.com");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(existingUser));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        userService.updateUser(1L, "updateduser", "updateduser@example.com");

        assertEquals("updateduser", existingUser.getUsername());
        assertEquals("updateduser@example.com", existingUser.getEmail());
    }

    @Test
    @DisplayName("Test Delete User")
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(user));

        userService.deleteUser(1L);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }
}
