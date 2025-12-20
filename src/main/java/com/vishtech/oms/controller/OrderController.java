package com.vishtech.oms.controller;


import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import com.vishtech.oms.entity.OrderEntity;
import com.vishtech.oms.exception.OrderNotFoundException;
import com.vishtech.oms.service.OrderService;
import com.vishtech.oms.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto request){
        OrderResponseDto response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable long id){
        OrderResponseDto orderResponseDto = orderService.getOrderById(id);
       return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping
    public Page<OrderResponseDto> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return orderService.getOrders(page, size, sortBy, direction);
    }
}
