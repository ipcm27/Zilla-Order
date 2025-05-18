package com.devigor.orderService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "T_ORDER_LINE_ITEMS")
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "skuCode cannot be null.")
    private String skuCode;

    @NotNull(message = "Price cannot be null.")
    @Positive(message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be null.")
    @Positive(message = "Quantity must be greater than zero.")
    private Integer quantity;

}
