package com.localbasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.DashboardResponseDTO;
import com.localbasket.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponseDTO> getDashboard() {

        return ResponseEntity.ok(
                adminService.getDashboard()
        );
    }

}
