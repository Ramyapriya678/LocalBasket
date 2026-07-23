package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.StoreProduct;
import com.localbasket.service.StoreProductService;

@RestController
@RequestMapping("/api/store-products")
public class StoreProductController {

    @Autowired
    private StoreProductService service;

    @PostMapping
    public StoreProduct addStoreProduct(@RequestBody StoreProduct storeProduct) {

        return service.save(storeProduct);
    }

    @GetMapping
    public List<StoreProduct> getAllStoreProducts() {

        return service.getAllStoreProducts();
    }

    @GetMapping("/{id}")
    public StoreProduct getStoreProductById(@PathVariable Long id) {

        return service.getStoreProductById(id);
    }

    @GetMapping("/store/{storeId}")
    public List<StoreProduct> getStoreProductsByStore(@PathVariable Long storeId) {

        return service.getStoreProductsByStore(storeId);
    }
    
    @GetMapping("/owner/{ownerId}")
    public List<StoreProduct> getMyStoreProducts(@PathVariable Long ownerId) {

        return service.getMyStoreProducts(ownerId);

    }
    

    @PutMapping("/{id}")
    public StoreProduct updateStoreProduct(@PathVariable Long id,
                                           @RequestBody StoreProduct storeProduct) {

        return service.updateStoreProduct(id, storeProduct);
    }

    @DeleteMapping("/{id}")
    public String deleteStoreProduct(@PathVariable Long id) {

        service.deleteStoreProduct(id);

        return "Store Product deleted successfully";
    }

}