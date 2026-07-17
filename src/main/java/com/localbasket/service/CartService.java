package com.localbasket.service;

import com.localbasket.entity.Cart;

public interface CartService {

    Cart addToCart(Long userId, Long productId, Integer quantity);

    Cart getCartByUser(Long userId);

    Cart updateCartItem(Long cartItemId, Integer quantity);

    void removeCartItem(Long cartItemId);

}