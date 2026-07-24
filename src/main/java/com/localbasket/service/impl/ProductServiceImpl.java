package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Category;
import com.localbasket.entity.Product;
import com.localbasket.repository.CategoryRepository;
import com.localbasket.repository.ProductRepository;
import com.localbasket.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public Product addProduct(Product product) {


        if (productRepository.existsByProductName(product.getProductName())) {
            throw new RuntimeException("Product already exists.");
        }


        if (product.getCategory() == null) {
            throw new RuntimeException("Category is required");
        }


        Category category;


        if (product.getCategory().getId() != null) {


            category = categoryRepository.findById(
                    product.getCategory().getId()
            )
            .orElseThrow(() ->
                    new RuntimeException("Category not found")
            );


        } else if (product.getCategory().getCategoryName() != null
                && !product.getCategory().getCategoryName().isEmpty()) {


            category = categoryRepository.findByCategoryName(
                    product.getCategory().getCategoryName()
            )
            .orElseThrow(() ->
                    new RuntimeException("Category not found")
            );


        } else {

            throw new RuntimeException("Category is required");

        }


        product.setCategory(category);



        if(product.getStatus() == null || product.getStatus().isEmpty()) {

            product.setStatus("ACTIVE");

        }


        return productRepository.save(product);
    }




    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();

    }




    @Override
    public Product getProductById(Long id) {


        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found.")
                );

    }




    @Override
    public Product updateProduct(Long id, Product product) {


        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found.")
                );


        if(product.getCategory() == null) {
            throw new RuntimeException("Category is required");
        }


        Category category;


        if(product.getCategory().getId() != null) {


            category = categoryRepository.findById(
                    product.getCategory().getId()
            )
            .orElseThrow(() ->
                    new RuntimeException("Category not found")
            );


        } else if(product.getCategory().getCategoryName() != null
                && !product.getCategory().getCategoryName().isEmpty()) {


            category = categoryRepository.findByCategoryName(
                    product.getCategory().getCategoryName()
            )
            .orElseThrow(() ->
                    new RuntimeException("Category not found")
            );


        } else {

            throw new RuntimeException("Category is required");

        }



        existing.setCategory(category);
        existing.setProductName(product.getProductName());
        existing.setBrand(product.getBrand());
        existing.setDescription(product.getDescription());
        existing.setImageUrl(product.getImageUrl());
        existing.setUnit(product.getUnit());
        existing.setStatus(product.getStatus());


        return productRepository.save(existing);

    }




    @Override
    public void deleteProduct(Long id) {


        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found.")
                );


        productRepository.delete(product);

    }

}