package com.localbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localbasket.entity.Inventory;
import com.localbasket.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public Inventory addInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/store/{storeId}")
    public List<Inventory> getByStore(@PathVariable Long storeId) {
        return inventoryService.getByStore(storeId);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody Inventory inventory) {

        return inventoryService.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id) {

        inventoryService.deleteInventory(id);
        return "Inventory deleted";
    }
}