package com.devigor.order_service.service;

import com.devigor.order_service.dto.OrderLineItemsDto;
import com.devigor.order_service.dto.OrderRequest;
import com.devigor.order_service.model.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderCode(UUID.randomUUID().toString());

    }
}
