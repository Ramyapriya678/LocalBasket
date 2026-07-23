package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.Order;
import com.localbasket.service.OrderService;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {


    @Autowired
    private OrderService orderService;



    // Create Order
    @PostMapping("/place")
    public Order placeOrder(
            @RequestParam Long userId,
            @RequestParam Long addressId) {

        return orderService.placeOrder(userId, addressId);
    }



    // Get all orders (Admin)
    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();

    }



    // Get order by id
    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);

    }



    // Customer orders
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(
            @PathVariable Long userId) {

        return orderService.getOrdersByUser(userId);

    }



    // Store Owner orders
    @GetMapping("/store/{storeId}")
    public List<Order> getOrdersByStore(
            @PathVariable Long storeId) {

        return orderService.getOrdersByStore(storeId);

    }



    // Update order status
    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {


        return orderService.updateOrderStatus(id, status);

    }



    // Delete order
    @DeleteMapping("/{id}")
    public String deleteOrder(
            @PathVariable Long id) {


        orderService.deleteOrder(id);


        return "Order deleted successfully";

    }

}