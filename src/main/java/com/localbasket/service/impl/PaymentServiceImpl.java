package com.localbasket.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.PaymentRequestDTO;
import com.localbasket.dto.PaymentResponseDTO;
import com.localbasket.entity.Order;
import com.localbasket.entity.Payment;
import com.localbasket.enums.PaymentMethod;
import com.localbasket.enums.PaymentStatus;
import com.localbasket.repository.OrderRepository;
import com.localbasket.repository.PaymentRepository;
import com.localbasket.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {

        // Find Order
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Check if payment already exists
        if (paymentRepository.findByOrderId(order.getId()).isPresent()) {
            throw new RuntimeException("Payment already exists for this order");
        }

        Payment payment = new Payment();

        payment.setOrder(order);

        payment.setPaymentMethod(request.getPaymentMethod());

        // Always take amount from Order
        payment.setAmount(
                java.math.BigDecimal.valueOf(order.getTotalAmount())
        );

        // COD Payment
        if (request.getPaymentMethod() == PaymentMethod.COD) {

            payment.setPaymentStatus(PaymentStatus.PENDING);

        } else {

            payment.setPaymentStatus(PaymentStatus.SUCCESS);

            payment.setTransactionId(
                    UUID.randomUUID().toString()
            );

            payment.setPaidAt(LocalDateTime.now());
        }

        Payment savedPayment = paymentRepository.save(payment);

        return mapToDTO(savedPayment);
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapToDTO(payment);
    }

    @Override
    public PaymentResponseDTO getPaymentByOrderId(Long orderId) {

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapToDTO(payment);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(Long id, String status) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        PaymentStatus paymentStatus =
                PaymentStatus.valueOf(status.toUpperCase());

        payment.setPaymentStatus(paymentStatus);

        if (paymentStatus == PaymentStatus.SUCCESS
                && payment.getPaidAt() == null) {

            payment.setPaidAt(LocalDateTime.now());

            if (payment.getTransactionId() == null) {
                payment.setTransactionId(UUID.randomUUID().toString());
            }
        }

        Payment updatedPayment = paymentRepository.save(payment);

        return mapToDTO(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentRepository.delete(payment);
    }

    private PaymentResponseDTO mapToDTO(Payment payment) {

        PaymentResponseDTO dto = new PaymentResponseDTO();

        dto.setId(payment.getId());
        dto.setOrderId(payment.getOrder().getId());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setTransactionId(payment.getTransactionId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setPaidAt(payment.getPaidAt());
        dto.setCreatedAt(payment.getCreatedAt());

        return dto;
    }
}