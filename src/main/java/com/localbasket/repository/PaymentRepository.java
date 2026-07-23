package com.localbasket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localbasket.entity.Payment;
import com.localbasket.enums.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);

    long countByPaymentStatus(PaymentStatus paymentStatus);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

}