package com.localbasket.dto;

import jakarta.validation.constraints.NotNull;

public class WishlistRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Store Product ID is required")
    private Long storeProductId;

    public WishlistRequestDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public Long getStoreProductId() {
        return storeProductId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }
}