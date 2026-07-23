package com.localbasket.service;

import java.util.List;

import com.localbasket.entity.StoreProduct;

public interface StoreProductService {


    StoreProduct save(StoreProduct storeProduct);


    List<StoreProduct> getAllStoreProducts();


    StoreProduct getStoreProductById(Long id);


    List<StoreProduct> getStoreProductsByStore(Long storeId);


    // Store Owner - get only own store products
    List<StoreProduct> getMyStoreProducts(Long ownerId);


    StoreProduct updateStoreProduct(Long id, StoreProduct storeProduct);


    void deleteStoreProduct(Long id);

}