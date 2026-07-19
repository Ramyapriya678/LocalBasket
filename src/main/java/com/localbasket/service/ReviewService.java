package com.localbasket.service;

import java.util.List;

import com.localbasket.dto.ReviewRequestDTO;
import com.localbasket.dto.ReviewResponseDTO;

public interface ReviewService {

    ReviewResponseDTO createReview(ReviewRequestDTO request);

    ReviewResponseDTO getReviewById(Long id);

    List<ReviewResponseDTO> getAllReviews();

    List<ReviewResponseDTO> getReviewsByUserId(Long userId);

    List<ReviewResponseDTO> getReviewsByStoreProductId(Long storeProductId);

    List<ReviewResponseDTO> getReviewsByOrderId(Long orderId);

    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO request);

    void deleteReview(Long id);
}