package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Address;
import com.localbasket.repository.AddressRepository;
import com.localbasket.repository.StoreRepository;
import com.localbasket.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found."));
    }

    @Override
    public Address updateAddress(Long id, Address address) {

        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found."));

        existing.setAddressLine1(address.getAddressLine1());
        existing.setAddressLine2(address.getAddressLine2());
        existing.setCity(address.getCity());
        existing.setState(address.getState());
        existing.setPincode(address.getPincode());
        existing.setCountry(address.getCountry());
        existing.setLatitude(address.getLatitude());
        existing.setLongitude(address.getLongitude());

        return addressRepository.save(existing);
    }

    @Override
    public void deleteAddress(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (storeRepository.existsByAddressId(id)) {
            throw new RuntimeException(
                    "Cannot delete address because it is assigned to a store.");
        }

        addressRepository.delete(address);
    }
}