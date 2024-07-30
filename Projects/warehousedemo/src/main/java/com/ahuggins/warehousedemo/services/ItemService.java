package com.ahuggins.warehousedemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ahuggins.warehousedemo.models.Administrator;
import com.ahuggins.warehousedemo.models.Item;
import com.ahuggins.warehousedemo.models.StoredItem;
import com.ahuggins.warehousedemo.models.StoredItemKey;
import com.ahuggins.warehousedemo.models.Warehouse;
import com.ahuggins.warehousedemo.repositories.StoredItemRepository;

@Service
public class ItemService {
    private StoredItemRepository repo;

    public ItemService(StoredItemRepository repo){
        this.repo = repo;
    }

    public List<StoredItem> getWarehouseItems(int adminId, int warehouseId) {
        // Create warehouse for comparator
        Warehouse warehouse = new Warehouse();
        warehouse.setAdministrator(new Administrator(adminId));
        warehouse.setId(warehouseId);

        return repo.findByWarehouse(warehouse); // Return result
    }

    public Optional<Item> getItemById(int itemId) {
        List<StoredItem> items = repo.findByItem(new Item(itemId)); // Grab items
        
        // Handle returns
        if(items.size() == 0) return Optional.empty();
        return Optional.of(items.get(0).getItem());
    }

    public Optional<Item> getItemByName(int adminId, int warehouseId, String name) {
        // Construct query components
        Item item = new Item();
        item.setName(name);
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseId);
        warehouse.setAdministrator(new Administrator(adminId));

        // Execute
        List<StoredItem> items = repo.findByItemAndWarehouse(item, warehouse);

        // Handle returns
        if(items.size() == 0) return Optional.empty();
        return Optional.of(items.get(0).getItem());
    }

    public Optional<StoredItem> addItemToWarehouse(int adminId, int warehouseId, StoredItem item) {
        // Construct query components
        Warehouse warehouse = new Warehouse();
        warehouse.setAdministrator(new Administrator(adminId));
        warehouse.setId(warehouseId);

        // Ensure does not exist
        if(repo.findByItemAndWarehouse(item.getItem(), warehouse).contains(item)) return Optional.empty();

        // Add to database
        item.setWarehouse(warehouse);
        return Optional.of(repo.save(item));
    }

    public Optional<StoredItem> updateWarehouseItem(int adminId, int warehouseId, int itemId, StoredItem item) {
        // Construct query components
        Warehouse warehouse = new Warehouse();
        warehouse.setAdministrator(new Administrator(adminId));
        warehouse.setId(warehouseId);

        // Ensure exists
        if(repo.findByItemAndWarehouse(item.getItem(), warehouse).contains(item)) {
            // Update
            item.setWarehouse(warehouse);
            return Optional.of(repo.save(item));
        }

        return Optional.empty();
    }

    public void removeItemFromWarehouse(int adminId, int warehouseId, int itemId) throws IllegalAccessException {
        // Ensure ownership
        Warehouse warehouse = new Warehouse();
        warehouse.setAdministrator(new Administrator(adminId));
        warehouse.setId(warehouseId);

        Item item = new Item(itemId);

        if(repo.findByItemAndWarehouse(item, warehouse).size() != 1) throw new IllegalAccessException();

        // Remove from database
        StoredItemKey itemKey = new StoredItemKey(itemId, warehouseId);
        repo.deleteById(itemKey);
    }
    
}
