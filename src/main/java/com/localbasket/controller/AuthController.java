package com.localbasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.LoginRequest;
import com.localbasket.dto.LoginResponse;
import com.localbasket.entity.User;
import com.localbasket.repository.DeliveryPartnerRepository;
import com.localbasket.security.JwtService;
import com.localbasket.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    // Register API
    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login API
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userService.login(request);

        // Generate JWT with email and role
        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().getName()
        );

        LoginResponse response = new LoginResponse(
                "Login Successful",
                token,
                user.getId(),
                user.getRole().getName()
        );

        // If Delivery Partner, return deliveryPartnerId
        if ("DELIVERY_PARTNER".equals(user.getRole().getName())) {

            deliveryPartnerRepository
                    .findByUserId(user.getId())
                    .ifPresent(partner ->
                            response.setDeliveryPartnerId(partner.getId()));
        }

        return response;
    }
}