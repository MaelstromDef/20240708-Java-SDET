package com.ahuggins.warehousedemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.warehousedemo.models.Item;

@RestController
@RequestMapping("/{adminId}/{warehouseId}/items")
public class ItemController {
    
}