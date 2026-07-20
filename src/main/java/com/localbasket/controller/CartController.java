package com.localbasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.Cart;
import com.localbasket.service.CartService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/api/cart")
@Validated
public class CartController {


    @Autowired
    private CartService cartService;



    // Add item to cart
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(

            @RequestParam
            @NotNull(message = "User ID is required")
            Long userId,


            @RequestParam
            @NotNull(message = "Store Product ID is required")
            Long storeProductId,


            @RequestParam
            @NotNull(message = "Quantity is required")
            @Min(value = 1, message = "Quantity must be at least 1")
            Integer quantity

    ) {


        Cart cart = cartService.addToCart(
                userId,
                storeProductId,
                quantity
        );


        return ResponseEntity.ok(cart);
    }




    // Get cart by user
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(

            @PathVariable Long userId

    ) {


        Cart cart = cartService.getCartByUser(userId);


        return ResponseEntity.ok(cart);
    }





    // Update quantity
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<Cart> update(

            @PathVariable Long cartItemId,


            @RequestParam
            @NotNull(message = "Quantity is required")
            @Min(value = 1, message = "Quantity must be at least 1")
            Integer quantity

    ) {


        Cart updatedCart = cartService.updateCartItem(
                cartItemId,
                quantity
        );


        return ResponseEntity.ok(updatedCart);
    }





    // Remove item
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> remove(

            @PathVariable Long cartItemId

    ) {


        cartService.removeCartItem(cartItemId);


        return ResponseEntity.ok(
                "Item removed successfully"
        );

    }

}