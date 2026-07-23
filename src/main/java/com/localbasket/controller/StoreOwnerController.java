package com.localbasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.StoreOwnerDashboardDTO;
import com.localbasket.service.StoreOwnerService;

@RestController
@RequestMapping("/api/store-owner")
@CrossOrigin(origins = "http://localhost:5173")
public class StoreOwnerController {

    @Autowired
    private StoreOwnerService storeOwnerService;

    

}