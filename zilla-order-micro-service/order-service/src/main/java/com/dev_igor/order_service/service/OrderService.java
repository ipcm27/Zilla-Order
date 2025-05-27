package com.dev_igor.order_service.service;

import com.dev_igor.order_service.dto.InventoryResponse;
import com.dev_igor.order_service.dto.OrderDto;
import com.dev_igor.order_service.dto.OrderLineItemsDto;
import com.dev_igor.order_service.dto.OrderRequest;
import com.dev_igor.order_service.model.Order;
import com.dev_igor.order_service.model.OrderLineItems;
import com.dev_igor.order_service.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private static final String INVENTORY_URL = "http://localhost:8086/api/inventory";

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderCode(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        if (allProductsInInventory(skuCodes)) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in Stock pelase try again later");
        }
    }

    protected List<InventoryResponse> getInventoryList(List<String> skuCodes) {
        return webClient.get()
                .uri(INVENTORY_URL,
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<InventoryResponse>>() {}).block();
    }

    protected boolean allProductsInInventory(List<String> skuCodes) {
        List<InventoryResponse> inventoryList = getInventoryList(skuCodes);
      return inventoryList.stream().allMatch(InventoryResponse::isInStock);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }



    public List<OrderDto> getAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderDto::mapToDto)
                .toList();
    }
}
