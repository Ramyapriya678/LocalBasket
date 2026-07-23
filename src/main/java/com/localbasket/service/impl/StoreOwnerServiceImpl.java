package com.localbasket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.StoreOwnerDashboardDTO;
import com.localbasket.entity.Store;
import com.localbasket.entity.StoreProduct;
import com.localbasket.repository.StoreRepository;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.service.StoreOwnerService;

import java.util.List;

@Service
public class StoreOwnerServiceImpl implements StoreOwnerService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Override
    public StoreOwnerDashboardDTO getDashboard(Long ownerId) {

        Store store = storeRepository.findByOwnerId(ownerId)
                .orElseThrow(() ->
                        new RuntimeException("Store not found."));

        List<StoreProduct> products =
                storeProductRepository.findByStoreId(store.getId());

        long totalProducts = products.size();

        long availableProducts = products.stream()
                .filter(p -> Boolean.TRUE.equals(p.getIsAvailable()))
                .count();

        long outOfStockProducts = products.stream()
                .filter(p -> p.getStockQuantity() == null || p.getStockQuantity() == 0)
                .count();

        return new StoreOwnerDashboardDTO(
                store.getId(),
                store.getStoreName(),
                totalProducts,
                availableProducts,
                outOfStockProducts,
                0,
                0,
                0,
                0
        );
    }
}