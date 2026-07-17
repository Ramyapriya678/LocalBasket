package com.localbasket.service;

import java.util.List;

import com.localbasket.entity.Category;

public interface CategoryService {

    // Create Category
    Category addCategory(Category category);

    // Get All Categories
    List<Category> getAllCategories();

    // Get Category By ID
    Category getCategoryById(Long id);

    // Update Category
    Category updateCategory(Long id, Category category);

    // Delete Category
    void deleteCategory(Long id);
}