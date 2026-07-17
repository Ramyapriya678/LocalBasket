package com.localbasket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductName(String productName);

    List<Product> findByCategoryId(Long categoryId);

}