package com.localbasket.service;

import java.util.List;

import com.localbasket.entity.Product;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

}