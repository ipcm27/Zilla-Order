package com.devIgor.inventoryService.service;

import com.devIgor.inventoryService.dto.InventoryResponse;
import com.devIgor.inventoryService.model.Inventory;
import com.devIgor.inventoryService.model.InventoryStatus;
import com.devIgor.inventoryService.repository.InventoryRepository;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }



    @Transactional(readOnly = true)
    public List<InventoryResponse> getStatusSkuCodes(List<String> skuCodeList) {

        Map<String, Inventory> inventoryMap = inventoryRepository
                .findBySkuCodeIn(skuCodeList)
                .stream()
                .collect(toMap(Inventory::getSkuCode, inv -> inv));

        return skuCodeList.stream().map(skuCode -> createInventoryResponse(skuCode, inventoryMap.get(skuCode))).toList();
    }

    protected InventoryResponse createInventoryResponse(String skuCode, @Nullable Inventory inventory) {
        if (inventory == null ){
            return InventoryResponse.builder()
                    .skuCode(skuCode)
                    .quantity(0)
                    .inventoryStatus(InventoryStatus.NOT_FOUND)
                    .build();
        }

         InventoryStatus status = inventory.getQuantity() > 0
                 ? InventoryStatus.IN_STOCK
                 : InventoryStatus.OUT_OF_STOCK;

            return InventoryResponse.builder()
                    .skuCode(skuCode)
                    .quantity(inventory.getQuantity())
                    .inventoryStatus(status)
                    .build();
    }
}
