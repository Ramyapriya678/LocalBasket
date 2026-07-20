package com.localbasket.service;

import java.util.List;

import com.localbasket.entity.Order;

public interface OrderService {

    // Place order from Cart
    Order placeOrder(Long userId, Long addressId);

    // Get all orders
    List<Order> getAllOrders();

    // Get order by id
    Order getOrderById(Long id);

    // Get orders by user
    List<Order> getOrdersByUser(Long userId);

    // Update order status
    Order updateOrderStatus(Long id, String status);

    // Delete order
    void deleteOrder(Long id);
}