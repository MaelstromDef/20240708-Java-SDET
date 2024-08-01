package com.ahuggins.warehousedemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahuggins.warehousedemo.models.Item;
import com.ahuggins.warehousedemo.models.StoredItem;
import com.ahuggins.warehousedemo.services.ItemService;

@CrossOrigin
@RestController
@RequestMapping("/{adminId}/{warehouseId}/items")
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
    @ResponseStatus(HttpStatus.FOUND)
    public Item getItemById(@RequestAttribute int adminId, @PathVariable int warehouseId, @PathVariable int itemId){
        Optional<Item> optional = service.getItemById(itemId);

        if(optional.isPresent()) return optional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/item")
    @ResponseStatus(HttpStatus.FOUND)
    public Item getItemByName(@RequestAttribute int adminId, @PathVariable int warehouseId, @RequestParam String name){
        Optional<Item> optional = service.getItemByName(adminId, warehouseId, name);

        if(optional.isPresent()) return optional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // POST METHODS

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoredItem addItemToWarehouse(@RequestAttribute int adminId, @PathVariable int warehouseId, @RequestBody StoredItem item){
        Optional<StoredItem> createdItem = service.addItemToWarehouse(adminId, warehouseId, item);

        if(createdItem.isPresent()) return createdItem.get();
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    // PUT METHODS

    @PutMapping
    public StoredItem updateWarehouseItem(@RequestAttribute int adminId, @PathVariable int warehouseId, @RequestBody StoredItem item){
        Optional<StoredItem> optional = service.updateWarehouseItem(adminId, warehouseId, item);

        if(optional.isPresent()) return optional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // DELETE METHODS

    @DeleteMapping("/{itemId}")
    public void removeItemFromWarehouse(@RequestAttribute int adminId, @PathVariable int warehouseId, @PathVariable int itemId){
        try {
            service.removeItemFromWarehouse(adminId, warehouseId, itemId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}