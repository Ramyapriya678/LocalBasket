package com.localbasket.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.WishlistRequestDTO;
import com.localbasket.dto.WishlistResponseDTO;
import com.localbasket.entity.StoreProduct;
import com.localbasket.entity.User;
import com.localbasket.entity.Wishlist;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.repository.UserRepository;
import com.localbasket.repository.WishlistRepository;
import com.localbasket.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Override
    public WishlistResponseDTO addToWishlist(WishlistRequestDTO request) {

        if (wishlistRepository.findByUserIdAndStoreProductId(
                request.getUserId(),
                request.getStoreProductId()).isPresent()) {

            throw new RuntimeException("Product already exists in wishlist.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        StoreProduct storeProduct = storeProductRepository.findById(request.getStoreProductId())
                .orElseThrow(() -> new RuntimeException("Store Product not found"));

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setStoreProduct(storeProduct);

        Wishlist saved = wishlistRepository.save(wishlist);

        return mapToDTO(saved);
    }

    @Override
    public WishlistResponseDTO getWishlistById(Long id) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        return mapToDTO(wishlist);
    }

    @Override
    public List<WishlistResponseDTO> getWishlistByUserId(Long userId) {

        return wishlistRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<WishlistResponseDTO> getAllWishlists() {

        return wishlistRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeFromWishlist(Long id) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        wishlistRepository.delete(wishlist);
    }

    private WishlistResponseDTO mapToDTO(Wishlist wishlist) {

        WishlistResponseDTO dto = new WishlistResponseDTO();

        dto.setId(wishlist.getId());
        dto.setUserId(wishlist.getUser().getId());
        dto.setStoreProductId(wishlist.getStoreProduct().getId());
        dto.setCreatedAt(wishlist.getCreatedAt());

        return dto;
    }
}