package com.localbasket.service;

import java.util.List;

import com.localbasket.dto.WishlistRequestDTO;
import com.localbasket.dto.WishlistResponseDTO;

public interface WishlistService {

    WishlistResponseDTO addToWishlist(WishlistRequestDTO request);

    WishlistResponseDTO getWishlistById(Long id);

    List<WishlistResponseDTO> getWishlistByUserId(Long userId);

    List<WishlistResponseDTO> getAllWishlists();

    void removeFromWishlist(Long id);
}