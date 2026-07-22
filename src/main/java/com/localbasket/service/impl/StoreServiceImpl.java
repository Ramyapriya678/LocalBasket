package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Store;
import com.localbasket.enums.StoreStatus;
import com.localbasket.repository.StoreRepository;
import com.localbasket.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
    }

    @Override
    public Store updateStore(Long id, Store store) {

        Store existingStore = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        existingStore.setStoreName(store.getStoreName());
        existingStore.setDescription(store.getDescription());
        existingStore.setEmail(store.getEmail());
        existingStore.setPhone(store.getPhone());
        existingStore.setOpeningTime(store.getOpeningTime());
        existingStore.setClosingTime(store.getClosingTime());
        existingStore.setStatus(store.getStatus());
        existingStore.setStoreImage(store.getStoreImage());
        existingStore.setAddress(store.getAddress());
        existingStore.setOwner(store.getOwner());

        return storeRepository.save(existingStore);
    }

    @Override
    public Store approveStore(Long id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setStatus(StoreStatus.APPROVED);

        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        storeRepository.delete(store);
    }
}