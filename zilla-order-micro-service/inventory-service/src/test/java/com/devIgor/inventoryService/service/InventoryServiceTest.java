package com.devIgor.inventoryService.service;

import com.devIgor.inventoryService.model.Inventory;
import com.devIgor.inventoryService.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    InventoryService inventoryService;

    @Test
    void isInInventory() {
        List<String> skuCodes = List.of("Iphone_13",
                "Banana");

        List<Inventory> inventory =  List.of(
                new Inventory(1L,"Iphone_13",10),

        )



    }


}