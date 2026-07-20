package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.ReviewRequestDTO;
import com.localbasket.dto.ReviewResponseDTO;
import com.localbasket.service.ReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ReviewResponseDTO createReview(@Valid @RequestBody ReviewRequestDTO request) {
        return reviewService.createReview(request);
    }

    @GetMapping("/{id}")
    public ReviewResponseDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/user/{userId}")
    public List<ReviewResponseDTO> getReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @GetMapping("/store-product/{storeProductId}")
    public List<ReviewResponseDTO> getReviewsByStoreProductId(@PathVariable Long storeProductId) {
        return reviewService.getReviewsByStoreProductId(storeProductId);
    }

    @GetMapping("/order/{orderId}")
    public List<ReviewResponseDTO> getReviewsByOrderId(@PathVariable Long orderId) {
        return reviewService.getReviewsByOrderId(orderId);
    }

    @PutMapping("/{id}")
    public ReviewResponseDTO updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDTO request) {

        return reviewService.updateReview(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {

        reviewService.deleteReview(id);
        return "Review deleted successfully.";
    }
}