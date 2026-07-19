package com.localbasket.dto;

import java.time.LocalDateTime;

public class WishlistResponseDTO {

    private Long id;
    private Long userId;
    private Long storeProductId;
    private LocalDateTime createdAt;

    public WishlistResponseDTO() {
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}