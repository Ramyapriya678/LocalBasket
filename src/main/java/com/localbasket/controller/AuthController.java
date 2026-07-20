package com.localbasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.LoginRequest;
import com.localbasket.dto.LoginResponse;
import com.localbasket.entity.User;
import com.localbasket.service.UserService;
import com.localbasket.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;


    // Register API
    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }


    // Login API
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {


        User user = userService.login(request);


        String token = jwtService.generateToken(user.getEmail());


        return new LoginResponse(
                "Login Successful",
                token
        );

    }
}