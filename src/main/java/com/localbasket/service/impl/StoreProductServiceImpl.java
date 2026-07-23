package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Product;
import com.localbasket.entity.Store;
import com.localbasket.entity.StoreProduct;
import com.localbasket.repository.ProductRepository;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.repository.StoreRepository;
import com.localbasket.service.StoreProductService;

@Service
public class StoreProductServiceImpl implements StoreProductService {


    @Autowired
    private StoreProductRepository repository;


    @Autowired
    private StoreRepository storeRepository;


    @Autowired
    private ProductRepository productRepository;



    @Override
    public StoreProduct save(StoreProduct storeProduct) {


        Long storeId = storeProduct.getStore().getId();

        Long productId = storeProduct.getProduct().getId();



        Store store = storeRepository.findById(storeId)
                .orElseThrow(() ->
                    new RuntimeException("Store not found with id : " + storeId)
                );



        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                    new RuntimeException("Product not found with id : " + productId)
                );



        storeProduct.setStore(store);

        storeProduct.setProduct(product);



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
                    new RuntimeException("Store Product not found")
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
    public StoreProduct updateStoreProduct(Long id, StoreProduct storeProduct) {


        StoreProduct existing = getStoreProductById(id);


        existing.setSellingPrice(storeProduct.getSellingPrice());

        existing.setStockQuantity(storeProduct.getStockQuantity());

        existing.setDiscountPercentage(storeProduct.getDiscountPercentage());

        existing.setIsAvailable(storeProduct.getIsAvailable());


        return repository.save(existing);

    }



    @Override
    public void deleteStoreProduct(Long id) {


        repository.deleteById(id);

    }

}