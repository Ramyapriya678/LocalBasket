package com.localbasket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUserId(Long userId);

    List<Review> findByStoreProductId(Long storeProductId);

    List<Review> findByOrderId(Long orderId);

}