package com.dev_igor.order_service.dto.request;

import com.dev_igor.order_service.model.Order;
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

    private Order order;

    @NotNull(message = "SKU Code cannot be null.")
    private String skuCode;

    @NotNull(message = "Price cannot be null.")
    @Positive(message = "Price must be greater than zero.")
    private double price;

    @NotNull(message = "Quantity cannot be null.")
    @Positive(message = "Quantity must be greater than zero.")
    private Integer quantity;

}
