package com.ahuggins.warehousedemo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.warehousedemo.models.Item;
import com.ahuggins.warehousedemo.models.StoredItem;
import com.ahuggins.warehousedemo.services.ItemService;

@RestController
@RequestMapping("/{adminId}/{warehouseId}")
public class ItemController {
    private ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    // GET METHODS

    @GetMapping
    public List<StoredItem> getWarehouseItems(@RequestAttribute int adminId, @PathVariable int warehouseId){
        return service.getWarehouseItems(adminId, warehouseId);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@RequestAttribute int adminId, @PathVariable int warehouseId, @PathVariable int itemId){
        return service.getItemById(itemId);
    }

    @GetMapping("/item")
    public Item getItemByName(@RequestAttribute int adminId, @PathVariable int warehouseId, @RequestParam String name){
        return service.getItemByName(adminId, warehouseId, name);
    }

    // POST METHODS

    @PostMapping
    public StoredItem addItemToWarehouse(@RequestAttribute int adminId, @PathVariable int warehouseId, @RequestBody StoredItem item){
        return service.addItemToWarehouse(adminId, warehouseId, item);
    }

    // PUT METHODS

    @PutMapping("/{itemId}")
    public StoredItem updateWarehouseItem(@RequestAttribute int adminId, @PathVariable int warehouseId, @PathVariable int itemId, @RequestBody StoredItem item){
        return service.updateWarehouseItem(adminId, warehouseId, itemId, item);
    }

    // DELETE METHODS

    @DeleteMapping("/{itemId}")
    public void removeItemFromWarehouse(@RequestAttribute int adminId, @PathVariable int warehouseId, @PathVariable int itemId)){
        return service.removeItemFromWarehouse(adminId, warehouseId, itemId);
    }
}