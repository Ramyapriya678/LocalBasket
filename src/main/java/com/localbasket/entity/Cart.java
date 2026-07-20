package com.localbasket.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User is required")
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Total amount cannot be negative")
    private @DecimalMin(value = "0.0", inclusive = true, message = "Total amount cannot be negative") BigDecimal totalAmount;

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public @DecimalMin(value = "0.0", inclusive = true, message = "Total amount cannot be negative") BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@DecimalMin(value = "0.0", inclusive = true, message = "Total amount cannot be negative") @DecimalMin(value = "0.0", inclusive = true, message = "Total amount cannot be negative") BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}