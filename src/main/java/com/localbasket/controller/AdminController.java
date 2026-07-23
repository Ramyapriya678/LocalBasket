package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.CreateStoreOwnerRequest;
import com.localbasket.dto.DashboardResponseDTO;
import com.localbasket.entity.User;
import com.localbasket.service.AdminService;
import com.localbasket.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponseDTO> getDashboard() {

        return ResponseEntity.ok(adminService.getDashboard());
    }

    @PostMapping("/store-owner")
    public User createStoreOwner(
            @RequestBody CreateStoreOwnerRequest request) {

        return userService.createStoreOwner(request);
    }

    @GetMapping("/store-owners")
    public List<User> getAllStoreOwners() {

        return userService.getAllStoreOwners();
    }
}