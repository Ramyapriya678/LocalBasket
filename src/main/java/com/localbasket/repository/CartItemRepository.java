package com.localbasket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndInventoryId(Long cartId, Long inventoryId);

}