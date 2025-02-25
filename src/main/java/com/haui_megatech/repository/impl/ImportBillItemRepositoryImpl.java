/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.haui_megatech.repository.impl;

import com.haui_megatech.ApplicationContext;
import com.haui_megatech.model.ImportBillItem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import com.haui_megatech.repository.ImportBillItemRepository;

/**
 *
 * @author dquy
 */
public class ImportBillItemRepositoryImpl implements ImportBillItemRepository {
    
    private final ApplicationContext applicationContext;
    
    private final String ABS_DATA_PATH;
    
    public ImportBillItemRepositoryImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        
        ABS_DATA_PATH = this.applicationContext.ABS_IMPORT_BILL_ITEMS_DATA_PATH;
        
        initCounter();
    }
    
    private void initCounter() {
        ArrayList<ImportBillItem> items = this.getAll();
        if (items.isEmpty()) {
            ImportBillItem.counter = 0;
        } else {
            ImportBillItem.counter = items.get(items.size() - 1).getId();
        }
    }
    
    private boolean saveToDisk(ArrayList<ImportBillItem> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ABS_DATA_PATH)
        )) {
            oos.writeObject(list);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    @Override
    public Optional<ImportBillItem> findById(Integer id) {
        return this.getAll()
                .parallelStream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<ImportBillItem> save(ImportBillItem item) {
        ArrayList<ImportBillItem> items = this.getAll();
        if (item.getId() != null) {
            int foundIndex = this.findIndexById(item.getId());
            ImportBillItem foundItem = items.get(foundIndex);
            update(foundItem, item);
            items.set(foundIndex, foundItem);
            return this.saveToDisk(items)
                    ? Optional.of(foundItem)
                    : Optional.empty();
        }

        item.setId(++ImportBillItem.counter);
        items.add(item);

        return this.saveToDisk(items)
                ? Optional.of(item)
                : Optional.empty();
    }
    
    private int findIndexById(Integer id) {
        ArrayList<ImportBillItem> items = this.getAll();
        for (int i = 0; i < items.size(); ++i) {
            if (items.get(i).getId().equals(id)) return i;
        }
        return -1;
    }
    
    private boolean update(ImportBillItem oldInfo, ImportBillItem newInfo) {
        oldInfo.setQuantity(newInfo.getQuantity());
        return true;
    }

    @Override
    public ArrayList<ImportBillItem> saveAll(ArrayList<ImportBillItem> items) {
        ArrayList<ImportBillItem> savedItems = new ArrayList<>();
        items.forEach(item -> {
            Optional<ImportBillItem> savedItem = this.save(item);
            if (savedItem.isPresent()) {
                savedItems.add(savedItem.get());
            }
        });
        return savedItems;
    }

    @Override
    public void deleteById(int id) {
        ArrayList<ImportBillItem> items = this.getAll();
        items.removeIf(item -> item.getId().equals(id));
        this.saveToDisk(items);
    }

    @Override
    public ArrayList<ImportBillItem> getAll() {
        ArrayList<ImportBillItem> products;
        try (ObjectInputStream ois = new ObjectInputStream(
                (new FileInputStream(ABS_DATA_PATH))
        )) {
            products = (ArrayList<ImportBillItem>) ois.readObject();
            if (products == null) {
                products = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList();
        }
        return products;
    }
    
}
