package com.localbasket.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.Order;


public interface OrderRepository 
        extends JpaRepository<Order, Long> {



    // Customer orders
    List<Order> findByUserId(
            Long userId);



    // Store owner orders
    List<Order> findByStoreId(
            Long storeId);


}