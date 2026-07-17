package com.localbasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

    boolean existsByStoreName(String storeName);

    // NEW
    boolean existsByAddressId(Long addressId);

}