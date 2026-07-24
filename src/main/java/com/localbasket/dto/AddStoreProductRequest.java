package com.localbasket.dto;


public class AddStoreProductRequest {


    private Long storeId;

    private String productName;

    private String category;

    private double price;

    private int stock;



    public Long getStoreId() {
        return storeId;
    }


    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

}