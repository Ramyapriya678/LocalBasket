package com.localbasket.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Cart;
import com.localbasket.entity.CartItem;
import com.localbasket.entity.Inventory;
import com.localbasket.entity.User;
import com.localbasket.repository.CartItemRepository;
import com.localbasket.repository.CartRepository;
import com.localbasket.repository.InventoryRepository;
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
    private InventoryRepository inventoryRepository;

    @Override
    public Cart addToCart(Long userId, Long productId, Integer quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setCartItems(new ArrayList<>());
            cart.setTotalAmount(0.0);
            cart = cartRepository.save(cart);
        }

        CartItem item = cartItemRepository
                .findByCartIdAndInventoryId(cart.getId(), inventory.getId())
                .orElse(null);

        if (item == null) {

            item = new CartItem();
            item.setCart(cart);
            item.setInventory(inventory);
            item.setQuantity(quantity);
            item.setPrice(inventory.getPrice());

            cart.getCartItems().add(item);

        } else {

            item.setQuantity(item.getQuantity() + quantity);

        }

        cartItemRepository.save(item);

        double total = 0.0;

        for (CartItem cartItem : cart.getCartItems()) {
            total += cartItem.getPrice() * cartItem.getQuantity();
        }

        cart.setTotalAmount(total);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUser(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        return cart;
    }

    @Override
    public Cart updateCartItem(Long cartItemId, Integer quantity) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        item.setQuantity(quantity);

        cartItemRepository.save(item);

        Cart cart = item.getCart();

        double total = 0.0;

        for (CartItem cartItem : cart.getCartItems()) {
            total += cartItem.getPrice() * cartItem.getQuantity();
        }

        cart.setTotalAmount(total);

        return cartRepository.save(cart);
    }

    @Override
    public void removeCartItem(Long cartItemId) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Cart cart = item.getCart();

        cart.getCartItems().remove(item);

        cartItemRepository.delete(item);

        double total = 0.0;

        for (CartItem cartItem : cart.getCartItems()) {
            total += cartItem.getPrice() * cartItem.getQuantity();
        }

        cart.setTotalAmount(total);

        cartRepository.save(cart);
    }
}