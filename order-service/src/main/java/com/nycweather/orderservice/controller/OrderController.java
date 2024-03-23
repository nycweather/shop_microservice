package com.nycweather.orderservice.controller;

import com.nycweather.orderservice.dto.OrderRequestDTO;
import com.nycweather.orderservice.dto.SingleOrderItemResponseDTO;
import com.nycweather.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        log.info("Order creation request received");
        return orderService.createOrder(orderRequestDTO);
    }

    @GetMapping("/all_orders")
    public ResponseEntity<Object> getAllOrders() {
        log.info("All orders are requested");
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrder(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/find-order")
    public ResponseEntity<Object> getOrderInformation(@RequestParam String productSku, @RequestParam String customerEmail) {
        List<SingleOrderItemResponseDTO> orderItem = orderService.getOrderInformation(productSku, customerEmail);
        if (orderItem.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Order item not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderItem.get(0), HttpStatus.OK);
    }
}
