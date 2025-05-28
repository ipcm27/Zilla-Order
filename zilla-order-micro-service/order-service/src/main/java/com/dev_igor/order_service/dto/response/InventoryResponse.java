package com.dev_igor.order_service.dto.response;

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
