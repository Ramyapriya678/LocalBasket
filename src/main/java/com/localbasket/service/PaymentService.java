package com.localbasket.service;

import java.util.List;

import com.localbasket.dto.PaymentRequestDTO;
import com.localbasket.dto.PaymentResponseDTO;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO request);

    PaymentResponseDTO getPaymentById(Long id);

    PaymentResponseDTO getPaymentByOrderId(Long orderId);

    List<PaymentResponseDTO> getAllPayments();

    PaymentResponseDTO updatePaymentStatus(Long id, String status);

    void deletePayment(Long id);
}