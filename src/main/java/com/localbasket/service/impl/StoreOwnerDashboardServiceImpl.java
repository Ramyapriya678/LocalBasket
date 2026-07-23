package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.StoreOwnerDashboardDTO;
import com.localbasket.entity.Order;
import com.localbasket.entity.Store;
import com.localbasket.entity.StoreProduct;
import com.localbasket.repository.OrderRepository;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.service.StoreOwnerDashboardService;
import com.localbasket.service.StoreService;


@Service
public class StoreOwnerDashboardServiceImpl 
        implements StoreOwnerDashboardService {



    @Autowired
    private StoreService storeService;


    @Autowired
    private StoreProductRepository storeProductRepository;


    @Autowired
    private OrderRepository orderRepository;



    @Override
    public StoreOwnerDashboardDTO getDashboard(Long ownerId) {


        Store store = storeService.getStoreByOwner(ownerId);



        List<StoreProduct> products =
                storeProductRepository.findByStoreId(store.getId());



        long totalProducts = products.size();



        long availableProducts =
                products.stream()
                .filter(p -> Boolean.TRUE.equals(p.getIsAvailable()))
                .count();



        long outOfStockProducts =
                products.stream()
                .filter(p -> p.getStockQuantity() == 0)
                .count();




        List<Order> orders =
                orderRepository.findByStoreId(store.getId());



        long totalOrders = orders.size();



        long pendingOrders =
                orders.stream()
                .filter(o ->
                    o.getStatus().equals("PLACED")
                    ||
                    o.getStatus().equals("CONFIRMED")
                )
                .count();



        long completedOrders =
                orders.stream()
                .filter(o ->
                    o.getStatus().equals("DELIVERED")
                )
                .count();



        double revenue =
                orders.stream()
                .filter(o ->
                    o.getStatus().equals("DELIVERED")
                )
                .mapToDouble(Order::getTotalAmount)
                .sum();




        return new StoreOwnerDashboardDTO(

                store.getId(),

                store.getStoreName(),

                totalProducts,

                availableProducts,

                outOfStockProducts,

                totalOrders,

                pendingOrders,

                completedOrders,

                revenue

        );

    }

}