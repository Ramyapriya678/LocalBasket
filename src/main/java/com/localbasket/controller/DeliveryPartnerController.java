package com.localbasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.DeliveryPartner;
import com.localbasket.repository.DeliveryPartnerRepository;

@RestController
@RequestMapping("/api/delivery-partners")
@CrossOrigin(origins = "*")
public class DeliveryPartnerController {


    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;



    @GetMapping("/user/{userId}")
    public ResponseEntity<DeliveryPartner> getByUserId(
            @PathVariable Long userId) {


        DeliveryPartner partner =
                deliveryPartnerRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Delivery Partner not found"
                    )
                );


        return ResponseEntity.ok(partner);
    }

}
