package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.DeliveryRequestDTO;
import com.localbasket.dto.DeliveryResponseDTO;
import com.localbasket.service.DeliveryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin(origins = "*")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // Assign Delivery Partner to Order
    @PostMapping("/assign")
    public ResponseEntity<DeliveryResponseDTO> assignDelivery(
            @Valid @RequestBody DeliveryRequestDTO request) {

        return ResponseEntity.ok(
                deliveryService.assignDelivery(request));
    }

    // Get Delivery by ID
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> getDeliveryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                deliveryService.getDeliveryById(id));
    }

    // Get Delivery by Order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<DeliveryResponseDTO> getDeliveryByOrderId(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                deliveryService.getDeliveryByOrderId(orderId));
    }

    // Get All Deliveries
    @GetMapping
    public ResponseEntity<List<DeliveryResponseDTO>> getAllDeliveries() {

        return ResponseEntity.ok(
                deliveryService.getAllDeliveries());
    }

    // Get Deliveries by Delivery Partner
    @GetMapping("/partner/{deliveryPartnerId}")
    public ResponseEntity<List<DeliveryResponseDTO>> getDeliveriesByPartner(
            @PathVariable Long deliveryPartnerId) {

        return ResponseEntity.ok(
                deliveryService.getDeliveriesByPartner(deliveryPartnerId));
    }

    // Update Delivery Status
    @PutMapping("/{id}/status")
    public ResponseEntity<DeliveryResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                deliveryService.updateDeliveryStatus(id, status));
    }

    // Update Live Location
    @PutMapping("/{id}/location")
    public ResponseEntity<DeliveryResponseDTO> updateLocation(
            @PathVariable Long id,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        return ResponseEntity.ok(
                deliveryService.updateLocation(id, latitude, longitude));
    }

    // Delete Delivery
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDelivery(
            @PathVariable Long id) {

        deliveryService.deleteDelivery(id);

        return ResponseEntity.ok("Delivery deleted successfully.");
    }
}