package com.devigor.order_service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "The orderLineItemsDtoList cannot be empty.")
    private List<OrderLineItemsDto> orderLineItemsDtoList;

}
