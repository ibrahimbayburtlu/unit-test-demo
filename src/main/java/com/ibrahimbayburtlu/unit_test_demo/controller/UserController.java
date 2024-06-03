package com.ibrahimbayburtlu.unit_test_demo.controller;

import com.ibrahimbayburtlu.unit_test_demo.Entity.User;
import com.ibrahimbayburtlu.unit_test_demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public void addUser(@RequestParam long id, @RequestParam String username, @RequestParam String email) {
        userService.addUser(id, username, email);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestParam String newUsername, @RequestParam String newEmail) {
        userService.updateUser(id, newUsername, newEmail);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
