package com.localbasket.dto;

import java.math.BigDecimal;

import com.localbasket.enums.PaymentMethod;

public class PaymentRequestDTO {

    private Long orderId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;

    public PaymentRequestDTO() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}