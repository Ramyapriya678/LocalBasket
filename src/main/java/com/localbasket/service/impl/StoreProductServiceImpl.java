package com.localbasket.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.AddStoreProductRequest;
import com.localbasket.entity.Product;
import com.localbasket.entity.Store;
import com.localbasket.entity.StoreProduct;
import com.localbasket.repository.ProductRepository;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.repository.StoreRepository;
import com.localbasket.service.StoreProductService;
import com.localbasket.entity.Category;
import com.localbasket.repository.CategoryRepository;

@Service
public class StoreProductServiceImpl implements StoreProductService {



    @Autowired
    private StoreProductRepository repository;


    @Autowired
    private StoreRepository storeRepository;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public StoreProduct addProductToStore(
            AddStoreProductRequest request) {


        Store store = storeRepository
                .findById(request.getStoreId())
                .orElseThrow(
                    () -> new RuntimeException("Store not found")
                );



        Category category = categoryRepository
                .findByCategoryName(request.getCategory())
                .orElseThrow(
                    () -> new RuntimeException("Category not found")
                );


        Product product = new Product();

        product.setProductName(request.getProductName());

        product.setCategory(category);

        product.setUnit("kg");

        product.setStatus("ACTIVE");


        product = productRepository.save(product);




        StoreProduct storeProduct = new StoreProduct();


        storeProduct.setStore(store);

        storeProduct.setProduct(product);



        storeProduct.setSellingPrice(
                BigDecimal.valueOf(request.getPrice())
        );


        storeProduct.setStockQuantity(
                request.getStock()
        );


        storeProduct.setDiscountPercentage(
                BigDecimal.ZERO
        );


        storeProduct.setIsAvailable(true);



        return repository.save(storeProduct);

    }






    @Override
    public StoreProduct save(StoreProduct storeProduct) {

        return repository.save(storeProduct);

    }





    @Override
    public List<StoreProduct> getAllStoreProducts() {

        return repository.findAll();

    }





    @Override
    public StoreProduct getStoreProductById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Store Product not found"
                    )
                );

    }






    @Override
    public List<StoreProduct> getStoreProductsByStore(Long storeId) {

        return repository.findByStoreId(storeId);

    }





    @Override
    public List<StoreProduct> getMyStoreProducts(Long ownerId) {

        return repository.findByStoreOwnerId(ownerId);

    }





    @Override
    public StoreProduct updateStoreProduct(
            Long id,
            StoreProduct storeProduct) {


        StoreProduct existing =
                getStoreProductById(id);



        existing.setSellingPrice(
                storeProduct.getSellingPrice()
        );


        existing.setStockQuantity(
                storeProduct.getStockQuantity()
        );


        existing.setDiscountPercentage(
                storeProduct.getDiscountPercentage()
        );


        existing.setIsAvailable(
                storeProduct.getIsAvailable()
        );



        return repository.save(existing);

    }






    @Override
    public void deleteStoreProduct(Long id) {

        repository.deleteById(id);

    }






    @Override
    public boolean existsByStoreAndProduct(
            Long storeId,
            Long productId) {


        return repository.existsByStoreIdAndProductId(
                storeId,
                productId
        );

    }


}