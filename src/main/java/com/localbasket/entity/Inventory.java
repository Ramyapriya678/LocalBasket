package com.localbasket.entity;

import jakarta.persistence.*;

@Entity
@Table(name="inventory")
public class Inventory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;


    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;


    @Column(nullable=false)
    private Integer quantity;


    @Column(nullable=false)
    private Double price;


    private String status="AVAILABLE";


    public Inventory(){

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id=id;
    }


    public Store getStore() {
        return store;
    }


    public void setStore(Store store) {
        this.store=store;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product=product;
    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity=quantity;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price=price;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status=status;
    }
}