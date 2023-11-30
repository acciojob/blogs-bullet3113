package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");

        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId){
        User user = userRepository3.findById(userId).orElse(null);
        if(user != null) {
            userRepository3.deleteById(userId);
        }
    }

    public User updateUser(Integer id, String password){
        User user = userRepository3.findById(id).orElse(null);
        if(user != null) {
            user.setPassword(password);
            userRepository3.save(user);
        }

        return user;
    }
}
