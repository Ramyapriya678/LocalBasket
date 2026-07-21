package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.StoreProduct;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.service.StoreProductService;

@Service
public class StoreProductServiceImpl implements StoreProductService {

    @Autowired
    private StoreProductRepository repository;

    @Override
    public StoreProduct save(StoreProduct storeProduct) {
        return repository.save(storeProduct);
    }

    @Override
    public List<StoreProduct> getAllStoreProducts() {
        return repository.findAll();
    }

    @Override
    public StoreProduct getStoreProductById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Store Product not found with id : " + id));
    }

    @Override
    public List<StoreProduct> getStoreProductsByStore(Long storeId) {

        return repository.findByStoreId(storeId);
    }

    @Override
    public StoreProduct updateStoreProduct(Long id, StoreProduct storeProduct) {

        StoreProduct existing = getStoreProductById(id);

        existing.setStore(storeProduct.getStore());
        existing.setProduct(storeProduct.getProduct());
        existing.setSellingPrice(storeProduct.getSellingPrice());
        existing.setStockQuantity(storeProduct.getStockQuantity());
        existing.setDiscountPercentage(storeProduct.getDiscountPercentage());
        existing.setIsAvailable(storeProduct.getIsAvailable());

        return repository.save(existing);
    }

    @Override
    public void deleteStoreProduct(Long id) {

        StoreProduct existing = getStoreProductById(id);

        repository.delete(existing);
    }

}