package com.localbasket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.Cart;
import com.localbasket.service.CartService;


@RestController
@RequestMapping("/api/cart")
public class CartController {



    @Autowired
    private CartService cartService;



    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {

        cartService.addToCart(userId, productId, quantity);
        return "Item Added Successfully";
    }



    @GetMapping("/{userId}")
    public Cart getCart(
            @PathVariable Long userId
    ){

        return cartService.getCartByUser(userId);
    }



    @PutMapping("/update/{cartItemId}")
    public Cart update(
            @PathVariable Long cartItemId,
            @RequestParam Integer quantity
    ){

        return cartService.updateCartItem(
                cartItemId,
                quantity
        );
    }



    @DeleteMapping("/remove/{cartItemId}")
    public String remove(
            @PathVariable Long cartItemId
    ){

        cartService.removeCartItem(cartItemId);

        return "Item removed successfully";
    }

}