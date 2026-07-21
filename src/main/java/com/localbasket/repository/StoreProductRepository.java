package com.localbasket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.StoreProduct;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {

    List<StoreProduct> findByStoreId(Long storeId);

}