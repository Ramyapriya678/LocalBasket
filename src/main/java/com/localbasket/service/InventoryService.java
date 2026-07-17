package com.localbasket.service;


import java.util.List;

import com.localbasket.entity.Inventory;


public interface InventoryService {


    Inventory addInventory(Inventory inventory);


    List<Inventory> getAllInventory();


    Inventory getInventoryById(Long id);


    List<Inventory> getByStore(Long storeId);


    Inventory updateInventory(Long id, Inventory inventory);


    void deleteInventory(Long id);

}