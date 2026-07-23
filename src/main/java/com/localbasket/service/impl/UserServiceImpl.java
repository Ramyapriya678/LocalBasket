package com.localbasket.service.impl;

import java.util.List;
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
import com.localbasket.dto.CreateStoreOwnerRequest;
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

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());

        return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);

    }
    
    @Override
    public User createStoreOwner(CreateStoreOwnerRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        // Check if phone already exists
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already exists.");
        }

        // Get STORE_OWNER role
        Role role = roleRepository.findByName("STORE_OWNER")
                .orElseThrow(() -> new RuntimeException("STORE_OWNER role not found"));

        // Create new user
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Assign STORE_OWNER role
        user.setRole(role);

        return userRepository.save(user);
    }
    @Override
    public List<User> getAllStoreOwners() {

        return userRepository.findByRole_Name("STORE_OWNER");

    }
    
}