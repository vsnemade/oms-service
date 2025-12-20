package com.vishtech.oms.service;

import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import com.vishtech.oms.entity.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto request);
    Optional<OrderEntity> getOrderById(Long orderId);

    public Page<OrderResponseDto> getOrders(int page,int size,String sortBy,String direction);
}
