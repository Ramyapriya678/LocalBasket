package com.localbasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.StoreProduct;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {

}