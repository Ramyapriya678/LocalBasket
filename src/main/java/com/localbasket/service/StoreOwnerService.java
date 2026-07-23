package com.localbasket.service;

import com.localbasket.dto.StoreOwnerDashboardDTO;

public interface StoreOwnerService {

    StoreOwnerDashboardDTO getDashboard(Long ownerId);

}