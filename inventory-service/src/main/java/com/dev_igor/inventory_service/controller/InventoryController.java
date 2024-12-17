package com.dev_igor.inventory_service.controller;


import com.dev_igor.inventory_service.repository.InventoryRepository;
import com.dev_igor.inventory_service.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping ("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInInventory(@PathVariable("sku-code") String sk){
        return inventoryService.isInInventory(sk);
    }
}
