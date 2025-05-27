package com.dev_igor.inventory_service.controller;


import com.dev_igor.inventory_service.dto.InventoryResponse;
import com.dev_igor.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getStatusSkuCodes(@RequestParam List<String> skuCodes){
        return inventoryService.getStatusSkuCodes(skuCodes);
    }


}
