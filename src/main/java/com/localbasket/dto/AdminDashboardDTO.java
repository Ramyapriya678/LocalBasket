package com.localbasket.dto;

public class AdminDashboardDTO {

    private long totalCustomers;
    private long totalStores;
    private long totalProducts;
    private long totalOrders;
    private double totalRevenue;

    public AdminDashboardDTO() {
    }

    public AdminDashboardDTO(long totalCustomers,
                             long totalStores,
                             long totalProducts,
                             long totalOrders,
                             double totalRevenue) {
        this.totalCustomers = totalCustomers;
        this.totalStores = totalStores;
        this.totalProducts = totalProducts;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getTotalStores() {
        return totalStores;
    }

    public void setTotalStores(long totalStores) {
        this.totalStores = totalStores;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}