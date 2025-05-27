package com.dev_igor.inventory_service.dto;

import com.dev_igor.inventory_service.model.InventoryStatus;
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
