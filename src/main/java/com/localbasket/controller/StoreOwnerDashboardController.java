package com.localbasket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.StoreOwnerDashboardDTO;
import com.localbasket.service.StoreOwnerDashboardService;



@RestController
@RequestMapping("/api/store-owner")
public class StoreOwnerDashboardController {



    @Autowired
    private StoreOwnerDashboardService dashboardService;



    @GetMapping("/dashboard/{ownerId}")
    public StoreOwnerDashboardDTO getDashboard(
            @PathVariable Long ownerId
    ) {


        return dashboardService.getDashboard(ownerId);

    }


}