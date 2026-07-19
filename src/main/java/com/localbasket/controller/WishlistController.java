package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.WishlistRequestDTO;
import com.localbasket.dto.WishlistResponseDTO;
import com.localbasket.service.WishlistService;

@RestController
@RequestMapping("/api/wishlists")
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public WishlistResponseDTO addToWishlist(@RequestBody WishlistRequestDTO request) {
        return wishlistService.addToWishlist(request);
    }

    @GetMapping("/{id}")
    public WishlistResponseDTO getWishlistById(@PathVariable Long id) {
        return wishlistService.getWishlistById(id);
    }

    @GetMapping("/user/{userId}")
    public List<WishlistResponseDTO> getWishlistByUserId(@PathVariable Long userId) {
        return wishlistService.getWishlistByUserId(userId);
    }

    @GetMapping
    public List<WishlistResponseDTO> getAllWishlists() {
        return wishlistService.getAllWishlists();
    }

    @DeleteMapping("/{id}")
    public String removeFromWishlist(@PathVariable Long id) {
        wishlistService.removeFromWishlist(id);
        return "Wishlist item removed successfully.";
    }
}