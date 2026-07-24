package com.localbasket.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localbasket.dto.AddStoreProductRequest;
import com.localbasket.entity.StoreProduct;
import com.localbasket.service.StoreProductService;


@RestController
@RequestMapping("/api/store-products")
public class StoreProductController {


    @Autowired
    private StoreProductService service;



    // Add new product to store
    @PostMapping
    public ResponseEntity<?> addStoreProduct(
            @RequestBody AddStoreProductRequest request) {


        try {


            StoreProduct savedProduct =
                    service.addProductToStore(request);


            return ResponseEntity.ok(savedProduct);


        }
        catch(RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }



    // Get all store products
    @GetMapping
    public List<StoreProduct> getAllStoreProducts() {

        return service.getAllStoreProducts();

    }




    // Get store product by id
    @GetMapping("/{id}")
    public StoreProduct getStoreProductById(
            @PathVariable Long id) {


        return service.getStoreProductById(id);

    }





    // Get products by store
    @GetMapping("/store/{storeId}")
    public List<StoreProduct> getStoreProductsByStore(
            @PathVariable Long storeId) {


        return service.getStoreProductsByStore(storeId);

    }





    // Get owner's store products
    @GetMapping("/owner/{ownerId}")
    public List<StoreProduct> getMyStoreProducts(
            @PathVariable Long ownerId) {


        return service.getMyStoreProducts(ownerId);

    }





    // Update store product
    @PutMapping("/{id}")
    public StoreProduct updateStoreProduct(
            @PathVariable Long id,
            @RequestBody StoreProduct storeProduct) {


        return service.updateStoreProduct(id, storeProduct);

    }





    // Delete store product
    @DeleteMapping("/{id}")
    public String deleteStoreProduct(
            @PathVariable Long id) {


        service.deleteStoreProduct(id);


        return "Store Product deleted successfully";

    }

}