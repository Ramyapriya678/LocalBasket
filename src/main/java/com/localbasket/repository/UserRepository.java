package com.localbasket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (used for login)
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Check if phone number already exists
    boolean existsByPhone(String phone);
    
    long countByRole_Name(String name);
    
    List<User> findByRole_Name(String roleName);
}