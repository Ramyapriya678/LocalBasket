package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Category;
import com.localbasket.repository.CategoryRepository;
import com.localbasket.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {

        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new RuntimeException("Category already exists.");
        }

        if (category.getStatus() == null || category.getStatus().isEmpty()) {
            category.setStatus("ACTIVE");
        }

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found."));
    }

    @Override
    public Category updateCategory(Long id, Category category) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found."));

        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setImageUrl(category.getImageUrl());
        existingCategory.setStatus(category.getStatus());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found."));

        categoryRepository.delete(category);
    }
}