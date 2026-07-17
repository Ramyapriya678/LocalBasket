package com.localbasket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);
}