package com.localbasket.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.ReviewRequestDTO;
import com.localbasket.dto.ReviewResponseDTO;
import com.localbasket.entity.Order;
import com.localbasket.entity.Review;
import com.localbasket.entity.StoreProduct;
import com.localbasket.entity.User;
import com.localbasket.repository.OrderRepository;
import com.localbasket.repository.ReviewRepository;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.repository.UserRepository;
import com.localbasket.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        StoreProduct storeProduct = storeProductRepository.findById(request.getStoreProductId())
                .orElseThrow(() -> new RuntimeException("Store Product not found"));

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Review review = new Review();

        review.setUser(user);
        review.setStoreProduct(storeProduct);
        review.setOrder(order);
        review.setRating(request.getRating());
        review.setReviewText(request.getReviewText());

        Review saved = reviewRepository.save(review);

        return mapToDTO(saved);
    }

    @Override
    public ReviewResponseDTO getReviewById(Long id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return mapToDTO(review);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByStoreProductId(Long storeProductId) {
        return reviewRepository.findByStoreProductId(storeProductId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByOrderId(Long orderId) {
        return reviewRepository.findByOrderId(orderId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO request) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRating(request.getRating());
        review.setReviewText(request.getReviewText());

        Review updated = reviewRepository.save(review);

        return mapToDTO(updated);
    }

    @Override
    public void deleteReview(Long id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }

    private ReviewResponseDTO mapToDTO(Review review) {

        ReviewResponseDTO dto = new ReviewResponseDTO();

        dto.setId(review.getId());
        dto.setUserId(review.getUser().getId());
        dto.setStoreProductId(review.getStoreProduct().getId());
        dto.setOrderId(review.getOrder().getId());
        dto.setRating(review.getRating());
        dto.setReviewText(review.getReviewText());
        dto.setCreatedAt(review.getCreatedAt());

        return dto;
    }
}