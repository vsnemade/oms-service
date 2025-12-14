package com.vishtech.oms.service;

import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService{

    private final ConcurrentHashMap<Long, OrderResponseDto> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    @Override
    public OrderResponseDto createOrder(OrderRequestDto request) {
        Long orderId = idGenerator.incrementAndGet();
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(orderId);
        response.setProductName(request.getProductName());
        response.setQuantity(request.getQuantity());
        response.setPrice(request.getPrice());
        response.setStatus("CREATED");
        response.setCreatedAt(LocalDateTime.now());
        store.put(orderId, response);
        return response;
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        return store.get(orderId);
    }
}
