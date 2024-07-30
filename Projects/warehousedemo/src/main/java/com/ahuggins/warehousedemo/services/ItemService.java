package com.ahuggins.warehousedemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ahuggins.warehousedemo.models.Item;
import com.ahuggins.warehousedemo.models.StoredItem;

@Service
public class ItemService {

    public List<StoredItem> getWarehouseItems(int adminId, int warehouseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWarehouseItems'");
    }

    public Item getItemById(int itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemById'");
    }

    public Item getItemByName(int adminId, int warehouseId, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemByName'");
    }

    public Item addItemToWarehouse(int adminId, int warehouseId, StoredItem item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemToWarehouse'");
    }

    public Object updateWarehouseItem(int adminId, int warehouseId, int itemId, StoredItem item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWarehouseItem'");
    }
    
}
