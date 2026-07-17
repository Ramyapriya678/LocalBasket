package com.localbasket.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Inventory;
import com.localbasket.repository.InventoryRepository;
import com.localbasket.repository.ProductRepository;
import com.localbasket.repository.StoreRepository;
import com.localbasket.service.InventoryService;


@Service
public class InventoryServiceImpl implements InventoryService {


    @Autowired
    private InventoryRepository inventoryRepository;


    @Autowired
    private StoreRepository storeRepository;


    @Autowired
    private ProductRepository productRepository;



    @Override
    public Inventory addInventory(Inventory inventory) {


        inventory.setStore(
            storeRepository.findById(
                inventory.getStore().getId()
            )
            .orElseThrow(
                () -> new RuntimeException("Store not found")
            )
        );


        inventory.setProduct(
            productRepository.findById(
                inventory.getProduct().getId()
            )
            .orElseThrow(
                () -> new RuntimeException("Product not found")
            )
        );


        return inventoryRepository.save(inventory);

    }



    @Override
    public List<Inventory> getAllInventory() {

        return inventoryRepository.findAll();

    }



    @Override
    public Inventory getInventoryById(Long id) {

        return inventoryRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("Inventory not found")
        );

    }



    @Override
    public List<Inventory> getByStore(Long storeId) {

        return inventoryRepository.findByStoreId(storeId);

    }



    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {


        Inventory old =
            inventoryRepository.findById(id)
            .orElseThrow(
                () -> new RuntimeException("Inventory not found")
            );


        old.setQuantity(inventory.getQuantity());

        old.setPrice(inventory.getPrice());


        return inventoryRepository.save(old);

    }



    @Override
    public void deleteInventory(Long id) {

        inventoryRepository.deleteById(id);

    }

}