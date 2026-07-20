package com.localbasket.service;

import java.util.List;

import com.localbasket.dto.PaymentRequestDTO;
import com.localbasket.dto.PaymentResponseDTO;

public interface PaymentService {

    // Create payment
    PaymentResponseDTO createPayment(PaymentRequestDTO request);

    // Get payment by payment ID
    PaymentResponseDTO getPaymentById(Long id);

    // Get payment using Order ID
    PaymentResponseDTO getPaymentByOrderId(Long orderId);

    // Get all payments
    List<PaymentResponseDTO> getAllPayments();

    // Update payment status
    PaymentResponseDTO updatePaymentStatus(Long id, String status);

    // Delete payment
    void deletePayment(Long id);
}