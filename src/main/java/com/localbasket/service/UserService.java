package com.localbasket.service;

import com.localbasket.dto.LoginRequest;
import com.localbasket.dto.LoginResponse;
import com.localbasket.entity.User;

public interface UserService {

    // Register a new user
    User registerUser(User user);

    // Login existing user
    LoginResponse login(LoginRequest request);
}