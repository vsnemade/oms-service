package com.vishtech.oms.service;

import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto request);
    OrderResponseDto getOrderById(Long orderId);
}
