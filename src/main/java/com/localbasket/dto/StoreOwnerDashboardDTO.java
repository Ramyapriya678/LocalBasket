package com.localbasket.dto;

public class StoreOwnerDashboardDTO {


    private Long storeId;

    private String storeName;

    private long totalProducts;

    private long availableProducts;

    private long outOfStockProducts;

    private long totalOrders;

    private long pendingOrders;

    private long completedOrders;

    private double totalRevenue;



    public StoreOwnerDashboardDTO() {
    }



    public StoreOwnerDashboardDTO(
            Long storeId,
            String storeName,
            long totalProducts,
            long availableProducts,
            long outOfStockProducts,
            long totalOrders,
            long pendingOrders,
            long completedOrders,
            double totalRevenue
    ) {

        this.storeId = storeId;
        this.storeName = storeName;
        this.totalProducts = totalProducts;
        this.availableProducts = availableProducts;
        this.outOfStockProducts = outOfStockProducts;
        this.totalOrders = totalOrders;
        this.pendingOrders = pendingOrders;
        this.completedOrders = completedOrders;
        this.totalRevenue = totalRevenue;

    }



    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }



    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }



    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }



    public long getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(long availableProducts) {
        this.availableProducts = availableProducts;
    }



    public long getOutOfStockProducts() {
        return outOfStockProducts;
    }

    public void setOutOfStockProducts(long outOfStockProducts) {
        this.outOfStockProducts = outOfStockProducts;
    }



    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }



    public long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }



    public long getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(long completedOrders) {
        this.completedOrders = completedOrders;
    }



    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

}