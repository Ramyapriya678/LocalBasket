package com.localbasket.service;

import com.localbasket.dto.StoreOwnerDashboardDTO;

public interface StoreOwnerDashboardService {

    StoreOwnerDashboardDTO getDashboard(Long ownerId);

}