package com.localbasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

