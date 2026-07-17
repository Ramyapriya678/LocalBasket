package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.User;
import com.localbasket.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}