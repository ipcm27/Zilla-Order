package com.dev_igor.order_service.service;

import com.dev_igor.order_service.dto.request.OrderLineItemsDto;
import com.dev_igor.order_service.dto.request.OrderRequest;
import com.dev_igor.order_service.dto.response.InventoryResponse;
import com.dev_igor.order_service.dto.response.InventoryStatus;
import com.dev_igor.order_service.model.Order;
import com.dev_igor.order_service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final String INVENTORY_URL = "http://localhost:8086/api/inventory";

    @Mock
    private OrderRepository orderRepository;


    @Mock
    private InventoryClient inventoryClient;

    @InjectMocks
    OrderService orderService;

    OrderRequest orderRequest;

    @BeforeEach
    void setup(){

        OrderLineItemsDto iphone = new OrderLineItemsDto(1L, "Iphone_13", 1200.00,10);
        OrderLineItemsDto samsung = new OrderLineItemsDto(2L, "Samsung_tv", 900.00,10);

        List<OrderLineItemsDto> items = List.of(iphone,samsung);
        this.orderRequest = new OrderRequest(items);

        List<String> skuCode = List.of("Iphone_13","Samsung_tv");
        InventoryResponse available = new InventoryResponse("Iphone_13", 10, InventoryStatus.IN_STOCK);
        InventoryResponse unavailable = new InventoryResponse("Samsung_tv", 10, InventoryStatus.OUT_OF_STOCK);

        when(inventoryClient.getInventoryStatus(skuCode)).thenReturn(List.of(available,unavailable));
    }

    @Test
    void shouldPlaceOrder() {

        orderService.placeOrder(orderRequest);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());

        Order savedOrder = orderCaptor.getValue();
        assertEquals(1,savedOrder.getOrderLineItemList().size());
        assertEquals("Iphone_13", savedOrder.getOrderLineItemList().get(0).getSkuCode());
    }


}