package com.localbasket.dto;

import java.math.BigDecimal;

public class DashboardResponseDTO {

    private Long totalUsers;
    private Long totalStores;
    private Long totalProducts;
    private Long totalOrders;
    private Long totalCategories;
    private Long totalPayments;
    private Long totalDeliveries;
    private BigDecimal totalRevenue;

    public DashboardResponseDTO() {
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Long getTotalStores() {
        return totalStores;
    }

    public void setTotalStores(Long totalStores) {
        this.totalStores = totalStores;
    }

    public Long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Long getTotalCategories() {
        return totalCategories;
    }

    public void setTotalCategories(Long totalCategories) {
        this.totalCategories = totalCategories;
    }

    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public Long getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(Long totalDeliveries) {
        this.totalDeliveries = totalDeliveries;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}