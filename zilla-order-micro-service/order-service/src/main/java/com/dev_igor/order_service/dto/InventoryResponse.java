package com.dev_igor.order_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryResponse {
    private String skuCode;
    private boolean isInStock;
}
