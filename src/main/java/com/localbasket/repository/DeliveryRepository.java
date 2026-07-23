package com.localbasket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localbasket.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    // Find delivery by Order ID
    Optional<Delivery> findByOrderId(Long orderId);

    // Find all deliveries assigned to a Delivery Partner
    List<Delivery> findByDeliveryPartnerId(Long deliveryPartnerId);
}