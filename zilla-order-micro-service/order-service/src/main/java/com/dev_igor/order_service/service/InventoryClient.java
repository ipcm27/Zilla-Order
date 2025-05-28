package com.dev_igor.order_service.service;

import com.dev_igor.order_service.dto.response.InventoryResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class InventoryClient {

    private final WebClient webClient;
    private static final String INVENTORY_URL = "http://localhost:8086/api/inventory";

    public InventoryClient(WebClient webClient) {
        this.webClient = webClient;
    }

    protected List<InventoryResponse> getInventoryStatus(List<String> skuCodes) {
        return webClient.get()
                .uri(INVENTORY_URL,
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<InventoryResponse>>() {}).block();
    }
}
