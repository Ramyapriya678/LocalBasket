package com.localbasket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.Address;
import com.localbasket.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "http://localhost:5173")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Add address for user
    @PostMapping("/add/{userId}")
    public ResponseEntity<Address> addAddress(
            @PathVariable Long userId,
            @RequestBody Address address) {

        return ResponseEntity.ok(
                addressService.addAddress(userId, address)
        );
    }

    // Get addresses by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getUserAddresses(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                addressService.getAddressesByUser(userId)
        );
    }

    // Get address by id
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                addressService.getAddressById(id)
        );
    }

    // Update address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long id,
            @RequestBody Address address) {

        return ResponseEntity.ok(
                addressService.updateAddress(id, address)
        );
    }

    // Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable Long id) {

        addressService.deleteAddress(id);

        return ResponseEntity.ok("Address deleted successfully");
    }
}