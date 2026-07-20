package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.Store;
import com.localbasket.service.StoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public Store addStore(@Valid @RequestBody Store store) {
        return storeService.addStore(store);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id,
                             @Valid @RequestBody Store store) {
        return storeService.updateStore(id, store);
    }

    @DeleteMapping("/{id}")
    public String deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return "Store deleted successfully.";
    }
}