package com.localbasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    boolean existsByStoreName(String storeName);

    // NEW
    boolean existsByAddressId(Long addressId);
    
    Optional<Store> findByOwnerId(Long ownerId);

}