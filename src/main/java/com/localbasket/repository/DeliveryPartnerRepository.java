package com.localbasket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Long> {

    Optional<DeliveryPartner> findByUserId(Long userId);

    Optional<DeliveryPartner> findByVehicleNumber(String vehicleNumber);

}