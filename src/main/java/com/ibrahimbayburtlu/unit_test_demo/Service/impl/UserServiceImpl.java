package com.ibrahimbayburtlu.unit_test_demo.Service.impl;

import com.ibrahimbayburtlu.unit_test_demo.Entity.User;
import com.ibrahimbayburtlu.unit_test_demo.Service.UserService;
import com.ibrahimbayburtlu.unit_test_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(long id, String username, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        return userRepository.save(newUser);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(Long id, String newUsername, String newEmail) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setUsername(newUsername);
            userToUpdate.setEmail(newEmail);
            userRepository.save(userToUpdate);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
