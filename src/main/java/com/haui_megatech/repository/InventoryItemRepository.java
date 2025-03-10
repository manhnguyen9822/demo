/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.haui_megatech.repository;

import com.haui_megatech.model.InventoryItem;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author dquy
 */
public interface InventoryItemRepository {
    
    Optional<InventoryItem> findById(Integer id);
    
    Optional<InventoryItem> save(InventoryItem item);
    
    ArrayList<InventoryItem> saveAll(ArrayList<InventoryItem> items);
    
    void deleteById(int id);
    
    ArrayList<InventoryItem> getAll();
}
