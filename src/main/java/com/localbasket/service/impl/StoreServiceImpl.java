package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Address;
import com.localbasket.entity.Store;
import com.localbasket.entity.User;
import com.localbasket.repository.AddressRepository;
import com.localbasket.repository.StoreRepository;
import com.localbasket.repository.UserRepository;
import com.localbasket.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Store addStore(Store store) {

        if (storeRepository.existsByStoreName(store.getStoreName())) {
            throw new RuntimeException("Store already exists.");
        }

        User owner = userRepository.findById(store.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Owner not found."));

        Address address = addressRepository.findById(store.getAddress().getId())
                .orElseThrow(() -> new RuntimeException("Address not found."));

        store.setOwner(owner);
        store.setAddress(address);

        // No need to set status.
        // Store entity already defaults it to PENDING.

        return storeRepository.save(store);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found."));
    }

    @Override
    public Store updateStore(Long id, Store store) {

        Store existing = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found."));

        User owner = userRepository.findById(store.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Owner not found."));

        Address address = addressRepository.findById(store.getAddress().getId())
                .orElseThrow(() -> new RuntimeException("Address not found."));

        existing.setOwner(owner);
        existing.setAddress(address);
        existing.setStoreName(store.getStoreName());
        existing.setDescription(store.getDescription());
        existing.setPhone(store.getPhone());
        existing.setEmail(store.getEmail());
        existing.setOpeningTime(store.getOpeningTime());
        existing.setClosingTime(store.getClosingTime());
        existing.setStoreImage(store.getStoreImage());

        if (store.getStatus() != null) {
            existing.setStatus(store.getStatus());
        }

        return storeRepository.save(existing);
    }

    @Override
    public void deleteStore(Long id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found."));

        storeRepository.delete(store);
    }
}