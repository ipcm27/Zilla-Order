package com.dev_igor.order_service.service;

import com.dev_igor.order_service.dto.request.OrderLineItemsDto;
import com.dev_igor.order_service.dto.request.OrderRequest;
import com.dev_igor.order_service.dto.response.InventoryResponse;
import com.dev_igor.order_service.dto.response.InventoryStatus;
import com.dev_igor.order_service.dto.response.OrderDto;
import com.dev_igor.order_service.model.Order;
import com.dev_igor.order_service.model.OrderLineItem;
import com.dev_igor.order_service.model.OrderStatus;
import com.dev_igor.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderService {

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        Order order = createOrder(orderRequest);
        List<String> skuCodes = order.getOrderLineItemList().stream().map(OrderLineItem::getSkuCode).toList();
        List<InventoryResponse> inventoryStatusList = inventoryClient.getInventoryStatus(skuCodes);

        Set<String> availableSkuCodes = inventoryStatusList.stream()
                .filter(inv -> inv.getInventoryStatus() == InventoryStatus.IN_STOCK)
                .map(InventoryResponse::getSkuCode)
                .collect(Collectors.toSet());

        Map<Boolean, List<OrderLineItem>> partitioned = order.getOrderLineItemList().stream()
                .collect(Collectors.partitioningBy(orderLineItem ->
                                availableSkuCodes.contains(orderLineItem.getSkuCode())
                ));

        List<OrderLineItem> availableItems = partitioned.get(true);
        List<OrderLineItem> unavailableItems = partitioned.get(false);

        order.setOrderLineItemList(availableItems);
        orderRepository.save(order);

    }

    Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderCode(UUID.randomUUID().toString());
        order.setStatus(OrderStatus.PENDING);
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(orderLine -> mapToDto(orderLine, order)).toList();
        order.setOrderLineItemList(orderLineItems);
        return order;
    }

    private OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto, Order order) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemsDto.getPrice());
        orderLineItem.setOrder(order);
        orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItem;
    }



    public List<OrderDto> getAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderDto::mapToDto)
                .toList();
    }
}
