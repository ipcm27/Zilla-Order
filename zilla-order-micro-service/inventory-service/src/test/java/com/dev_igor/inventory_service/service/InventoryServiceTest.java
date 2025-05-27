package com.dev_igor.inventory_service.service;

import com.dev_igor.inventory_service.dto.InventoryResponse;
import com.dev_igor.inventory_service.model.Inventory;
import com.dev_igor.inventory_service.model.InventoryStatus;
import com.dev_igor.inventory_service.repository.InventoryRepository;
import org.hibernate.AssertionFailure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    InventoryService inventoryService;

    private List<Inventory> inventoryList;

    private Map<String, Inventory> inventoryMap;

    @BeforeEach
    void setup(){
        this.inventoryList = List.of(
                new Inventory(1L, "Iphone_13", 10),
                new Inventory(2L, "Samsung_tv", 0)
        );

        inventoryMap = this.inventoryList
                .stream()
                .collect(toMap(Inventory::getSkuCode, inv -> inv));
    }

    @Test
    void shouldReturnFoundedSkuCode() {
        List<String> skuCodeList = List.of("Iphone_13", "Samsung_tv");

        when(inventoryRepository.findBySkuCodeIn(skuCodeList)).thenReturn(inventoryList);

        List<InventoryResponse> expected = skuCodeList.stream().map(skuCode -> this.inventoryService.createInventoryResponse(skuCode, inventoryMap.get(skuCode))).toList();

        List<InventoryResponse> result = this.inventoryService.getStatusSkuCodes(skuCodeList);

        assertEquals(result,expected);

    }

    @Test
    void shouldReturnThatOneItemWasNotFound() {
        List<String> skuCodeList = List.of("Iphone_13", "Samsung_tv", "Banana");

        when(inventoryRepository.findBySkuCodeIn(skuCodeList)).thenReturn(inventoryList);

        List<InventoryResponse> result = this.inventoryService.getStatusSkuCodes(skuCodeList);

        InventoryResponse bananaResponse = result.stream()
                .filter(response -> "Banana".equals(response.getSkuCode()))
                .findFirst()
                .orElseThrow(() -> new AssertionFailure("Banana not found"));

        assertEquals(InventoryStatus.NOT_FOUND, bananaResponse.getInventoryStatus());
        assertEquals(0, bananaResponse.getQuantity());
        assertEquals(3, result.size());
    }

    @Test
    void shouldReturnIsInStockFalse() {
        List<String> skuCodeList = List.of("Iphone_13", "Samsung_tv");

        when(inventoryRepository.findBySkuCodeIn(skuCodeList)).thenReturn(inventoryList);

        List<InventoryResponse> result = this.inventoryService.getStatusSkuCodes(skuCodeList);

        InventoryResponse samsungTv = result.stream()
                .filter(iventoryItem -> iventoryItem.getSkuCode().equals("Samsung_tv"))
                .findFirst()
                .orElseThrow(()-> new AssertionFailure("Samsung TV Not Found"));


        assertEquals(InventoryStatus.OUT_OF_STOCK, samsungTv.getInventoryStatus());
        assertEquals(0, samsungTv.getQuantity());
        assertEquals(2, result.size());

    }


}