package com.localbasket.service;

import java.util.List;

import com.localbasket.entity.Address;

public interface AddressService {

    Address addAddress(Long userId, Address address);

    List<Address> getAddressesByUser(Long userId);

    Address getAddressById(Long id);

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);

}