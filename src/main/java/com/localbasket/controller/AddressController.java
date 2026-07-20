package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.Address;
import com.localbasket.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;


    // Add Address
    @PostMapping
    public Address addAddress(@Valid @RequestBody Address address) {
        return addressService.addAddress(address);
    }


    // Get All Addresses
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }


    // Get Address By ID
    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }


    // Update Address
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id,
                                 @Valid @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }


    // Delete Address
    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return "Address deleted successfully.";
    }
}