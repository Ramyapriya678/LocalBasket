package com.localbasket.service;

import com.localbasket.dto.LoginRequest;
import com.localbasket.entity.User;

import java.util.List;

import com.localbasket.dto.CreateStoreOwnerRequest;

public interface UserService {

    // Register
    User registerUser(User user);

    // Login
    User login(LoginRequest request);

    // Get User by Id
    User getUserById(Long id);

    // Update User
    User updateUser(Long id, User user);
    
    void deleteUser(Long id);
    
    User createStoreOwner(CreateStoreOwnerRequest request);
    
    List<User> getAllStoreOwners();
}