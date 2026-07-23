package com.localbasket.dto;

public class LoginResponse {

    private String message;
    private String token;
    private Long userId;
    private String role;


   


    private Long storeId;
    private Long deliveryPartnerId;


    public LoginResponse() {
    }

    public LoginResponse(
            String message,
            String token,
            Long userId,
            String role
    ) {
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getDeliveryPartnerId() {
        return deliveryPartnerId;
    }

    public void setDeliveryPartnerId(Long deliveryPartnerId) {
        this.deliveryPartnerId = deliveryPartnerId;
    }

}