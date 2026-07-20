package com.localbasket.service;

import com.localbasket.dto.LoginRequest;
import com.localbasket.entity.User;

public interface UserService {

    // Register a new user
    User registerUser(User user);

    // Login existing user
    User login(LoginRequest request);
}