package com.localbasket.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.localbasket.entity.Address;
import com.localbasket.entity.User;
import com.localbasket.repository.AddressRepository;
import com.localbasket.repository.UserRepository;
import com.localbasket.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(
            AddressRepository addressRepository,
            UserRepository userRepository) {

        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Address addAddress(Long userId, Address address) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        address.setUser(user);

        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressesByUser(Long userId) {

        return addressRepository.findByUserId(userId);
    }

    @Override
    public Address getAddressById(Long id) {

        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public Address updateAddress(Long id, Address address) {

        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

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

        addressRepository.deleteById(id);
    }
}