package com.localbasket.dto;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    private Long id;
    private Long userId;
    private Long storeProductId;
    private Long orderId;
    private Integer rating;
    private String reviewText;
    private LocalDateTime createdAt;

    public ReviewResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getStoreProductId() {
        return storeProductId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}