package com.localbasket.service;

import java.util.List;

import com.localbasket.entity.Store;

public interface StoreService {

    Store addStore(Store store);

    List<Store> getAllStores();

    Store getStoreById(Long id);

    Store updateStore(Long id, Store store);

    void deleteStore(Long id);

}