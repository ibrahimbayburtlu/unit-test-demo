package com.ibrahimbayburtlu.unit_test_demo.Service;

import com.ibrahimbayburtlu.unit_test_demo.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User addUser(long id, String username, String email);

    User getUser(Long id);

    void updateUser(Long id, String newUsername, String newEmail);

    void deleteUser(Long id);
}
