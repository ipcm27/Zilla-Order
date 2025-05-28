package com.dev_igor.order_service.controller;

import com.dev_igor.order_service.dto.response.OrderDto;
import com.dev_igor.order_service.dto.request.OrderRequest;
import com.dev_igor.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/place-order")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
            orderService.placeOrder(orderRequest);
            return "Order `Placed Successfully";
    }

    @GetMapping(value = "/get-all-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

}
