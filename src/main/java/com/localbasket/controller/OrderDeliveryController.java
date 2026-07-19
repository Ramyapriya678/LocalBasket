package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.OrderDeliveryRequestDTO;
import com.localbasket.dto.OrderDeliveryResponseDTO;
import com.localbasket.service.OrderDeliveryService;

@RestController
@RequestMapping("/api/order-deliveries")
@CrossOrigin(origins = "*")
public class OrderDeliveryController {

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    @PostMapping
    public OrderDeliveryResponseDTO assignDeliveryPartner(
            @RequestBody OrderDeliveryRequestDTO request) {

        return orderDeliveryService.assignDeliveryPartner(request);
    }

    @GetMapping("/{id}")
    public OrderDeliveryResponseDTO getDeliveryById(
            @PathVariable Long id) {

        return orderDeliveryService.getDeliveryById(id);
    }

    @GetMapping("/order/{orderId}")
    public OrderDeliveryResponseDTO getDeliveryByOrderId(
            @PathVariable Long orderId) {

        return orderDeliveryService.getDeliveryByOrderId(orderId);
    }

    @GetMapping
    public List<OrderDeliveryResponseDTO> getAllDeliveries() {

        return orderDeliveryService.getAllDeliveries();
    }

    @PutMapping("/{id}/status")
    public OrderDeliveryResponseDTO updateDeliveryStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return orderDeliveryService.updateDeliveryStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteDelivery(
            @PathVariable Long id) {

        orderDeliveryService.deleteDelivery(id);
        return "Delivery deleted successfully.";
    }
}