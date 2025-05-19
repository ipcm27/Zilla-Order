package com.devIgor.inventoryService.service;

import com.devIgor.inventoryService.dto.InventoryResponse;
import com.devIgor.inventoryService.model.Inventory;
import com.devIgor.inventoryService.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    // TODO: APrimorar lógica pra ver se tem item ou nao. Atualmente
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInInventory(List<String> skuCodeList){

        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodeList);
        return inventoryList
                .stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
