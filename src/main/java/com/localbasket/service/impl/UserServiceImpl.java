package com.localbasket.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.localbasket.dto.LoginRequest;
import com.localbasket.entity.Role;
import com.localbasket.entity.User;
import com.localbasket.repository.RoleRepository;
import com.localbasket.repository.UserRepository;
import com.localbasket.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User registerUser(User user) {

        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        // Check if phone already exists
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new RuntimeException("Phone number already exists.");
        }


        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        // Assign CUSTOMER role automatically
        Role role = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role CUSTOMER not found"));

        user.setRole(role);


        return userRepository.save(user);
    }



    @Override
    public User login(LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());


        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }


        User user = optionalUser.get();


        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid password");
        }


        return user;
    }
}