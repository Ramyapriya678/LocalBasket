package com.localbasket.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Cart;
import com.localbasket.entity.CartItem;
import com.localbasket.entity.StoreProduct;
import com.localbasket.entity.User;
import com.localbasket.repository.CartItemRepository;
import com.localbasket.repository.CartRepository;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.repository.UserRepository;
import com.localbasket.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Override
    public Cart addToCart(Long userId, Long storeProductId, Integer quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id : " + userId));

        StoreProduct storeProduct = storeProductRepository.findById(storeProductId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Store product not found with id : " + storeProductId));

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {

            cart = new Cart();
            cart.setUser(user);
            cart.setCartItems(new ArrayList<>());
            cart.setTotalAmount(BigDecimal.ZERO);

            cart = cartRepository.save(cart);
        }

        CartItem item = cartItemRepository
                .findByCartIdAndStoreProductId(
                        cart.getId(),
                        storeProductId)
                .orElse(null);

        if (item == null) {

            item = new CartItem();

            item.setCart(cart);
            item.setStoreProduct(storeProduct);
            item.setQuantity(quantity);
            item.setPrice(storeProduct.getSellingPrice());

            item.setSubtotal(
                    storeProduct.getSellingPrice()
                            .multiply(BigDecimal.valueOf(quantity)));

            cart.getCartItems().add(item);

        } else {

            item.setQuantity(item.getQuantity() + quantity);

            item.setSubtotal(
                    item.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        cartItemRepository.save(item);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {

            totalAmount = totalAmount.add(cartItem.getSubtotal());
        }

        cart.setTotalAmount(totalAmount);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUser(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {

            User user = userRepository.findById(userId)
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            cart = new Cart();
            cart.setUser(user);
            cart.setCartItems(new ArrayList<>());
            cart.setTotalAmount(BigDecimal.ZERO);

            cart = cartRepository.save(cart);
        }

        return cart;
    }

    @Override
    public Cart updateCartItem(Long cartItemId, Integer quantity) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));

        item.setQuantity(quantity);

        item.setSubtotal(
                item.getPrice()
                        .multiply(BigDecimal.valueOf(quantity)));

        cartItemRepository.save(item);

        Cart cart = item.getCart();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {

            totalAmount = totalAmount.add(cartItem.getSubtotal());
        }

        cart.setTotalAmount(totalAmount);

        return cartRepository.save(cart);
    }

    @Override
    public void removeCartItem(Long cartItemId) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));

        Cart cart = item.getCart();

        cart.getCartItems().remove(item);

        cartItemRepository.delete(item);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {

            totalAmount = totalAmount.add(cartItem.getSubtotal());
        }

        cart.setTotalAmount(totalAmount);

        cartRepository.save(cart);
    }
}