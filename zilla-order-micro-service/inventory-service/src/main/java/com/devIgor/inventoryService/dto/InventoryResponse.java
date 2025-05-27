package com.devIgor.inventoryService.dto;

import com.devIgor.inventoryService.model.InventoryStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryResponse {
    private String skuCode;
    private Integer quantity;
    private InventoryStatus inventoryStatus;
}
