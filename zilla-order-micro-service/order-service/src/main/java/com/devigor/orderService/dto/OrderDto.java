package com.devigor.orderService.dto;

import com.devigor.orderService.model.Order;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private String orderCode;

    @NotEmpty(message = "The orderLineItemsDtoList cannot be empty.")
    private List<OrderLineItemsDto> orderLineItemsDtoList;

    public static OrderDto mapToDto(Order order) {
        List<OrderLineItemsDto> lineITemsDto = order.getOrderLineItemsList().stream()
                .map(item -> new OrderLineItemsDto(
                        item.getId(),
                        item.getSkuCode(),
                        item.getPrice(),
                        item.getQuantity()
                ))
                .toList();

                return new OrderDto(order.getOrderCode(), lineITemsDto);
    }

}
