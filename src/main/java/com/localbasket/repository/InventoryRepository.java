package com.localbasket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.localbasket.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByStoreId(Long storeId);

    @Query("SELECT i FROM Inventory i WHERE i.product.id = :productId")
    Optional<Inventory> findByProductId(@Param("productId") Long productId);
}