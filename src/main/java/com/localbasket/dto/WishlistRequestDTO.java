package com.localbasket.dto;

public class WishlistRequestDTO {

    private Long userId;
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