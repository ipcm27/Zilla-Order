package com.dev_igor.order_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "T_ORDER_LINE_ITEMS")
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Order order;

    @NotNull(message = "skuCode cannot be null.")
    private String skuCode;

    @NotNull(message = "Price cannot be null.")
    @Positive(message = "Price must be greater than zero.")
    private double price;

    @NotNull(message = "Quantity cannot be null.")
    @Positive(message = "Quantity must be greater than zero.")
    private Integer quantity;

}
