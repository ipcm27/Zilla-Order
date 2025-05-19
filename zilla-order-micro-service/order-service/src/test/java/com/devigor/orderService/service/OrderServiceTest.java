package com.devigor.orderService.service;

import com.devigor.orderService.dto.InventoryResponse;
import com.devigor.orderService.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private final String INVENTORY_URL = "http://localhost:8086/api/inventory";

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private WebClient webClient;

    @InjectMocks
    OrderService orderService;


    @Test
    void shouldReturnFalseIFProductNotIInventory() {

        OrderService spyService = Mockito.spy(orderService);

       List<String> skuCodes = List.of("Iphone_13",
               "Banana");

        List<InventoryResponse> mockResponse = List.of(
                new InventoryResponse("Iphone_13", true),
                new InventoryResponse("Banana", false)
        );

        Mockito.doReturn(mockResponse)
                .when(spyService)
                .getInventoryList(skuCodes);

        boolean result = spyService.allProductsInInventory(skuCodes);
        assertFalse(result);

    }

    @Test
    void shouldReturnTrueIfProductNotIInventory() {

        OrderService spyService = Mockito.spy(orderService);

        List<String> skuCodes = List.of("Iphone_13",
                "Banana");

        List<InventoryResponse> mockResponse = List.of(
                new InventoryResponse("Iphone_13", true),
                new InventoryResponse("Banana", true)
        );

        Mockito.doReturn(mockResponse)
                .when(spyService)
                .getInventoryList(skuCodes);

        boolean result = spyService.allProductsInInventory(skuCodes);
        assertTrue(result);

    }


}