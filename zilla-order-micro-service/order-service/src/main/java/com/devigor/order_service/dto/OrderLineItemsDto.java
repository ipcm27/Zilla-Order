package com.devigor.order_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


import java.math.BigDecimal;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

    private Long id;

    @NotNull(message = "SKU Code cannot be null.")
    private String skuCode;

    @NotNull(message = "Price cannot be null.")
    @Positive(message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be null.")
    @Positive(message = "Quantity must be greater than zero.")
    private Integer quantity;

}
