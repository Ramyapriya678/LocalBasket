package com.localbasket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.OrderDelivery;

public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {

    Optional<OrderDelivery> findByOrderId(Long orderId);

    Optional<OrderDelivery> findByDeliveryPartnerId(Long deliveryPartnerId);

}