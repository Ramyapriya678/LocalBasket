package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.DeliveryPartnerResponseDTO;
import com.localbasket.entity.DeliveryPartner;
import com.localbasket.repository.DeliveryPartnerRepository;
import com.localbasket.service.DeliveryPartnerService;

@RestController
@RequestMapping("/api/delivery-partners")
@CrossOrigin(origins = "*")
public class DeliveryPartnerController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    // Get all delivery partners (for dropdown)
    @GetMapping
    public ResponseEntity<List<DeliveryPartnerResponseDTO>> getAllDeliveryPartners() {

        return ResponseEntity.ok(
                deliveryPartnerService.getAllDeliveryPartners()
        );
    }

    // Get delivery partner by id
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryPartner> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                deliveryPartnerService.getDeliveryPartnerById(id)
        );
    }

    // Get delivery partner by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<DeliveryPartner> getByUserId(
            @PathVariable Long userId) {

        DeliveryPartner partner = deliveryPartnerRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Delivery Partner not found"));

        return ResponseEntity.ok(partner);
    }
}