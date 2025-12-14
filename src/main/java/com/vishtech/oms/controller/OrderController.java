package com.vishtech.oms.controller;


import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import com.vishtech.oms.exception.OrderNotFoundException;
import com.vishtech.oms.service.OrderService;
import com.vishtech.oms.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
        OrderResponseDto response= orderService.getOrderById(id);

        if(Objects.isNull(response))  throw new OrderNotFoundException(id);

        return ResponseEntity.ok(response);
    }

}
