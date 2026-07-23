package com.localbasket.service;

import java.util.List;

import com.localbasket.entity.Order;


public interface OrderService {


    // Place order
    Order placeOrder(
            Long userId,
            Long addressId);



    // All orders
    List<Order> getAllOrders();



    // Order by id
    Order getOrderById(
            Long id);



    // Customer orders
    List<Order> getOrdersByUser(
            Long userId);



    // Store owner orders
    List<Order> getOrdersByStore(
            Long storeId);



    // Update status
    Order updateOrderStatus(
            Long id,
            String status);



    // Delete
    void deleteOrder(
            Long id);

}