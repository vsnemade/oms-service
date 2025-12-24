package com.vishtech.oms.service;

import com.vishtech.oms.config.OmsProperties;
import com.vishtech.oms.domain.OrderStatus;
import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import com.vishtech.oms.entity.OrderEntity;
import com.vishtech.oms.exception.InvalidOrderException;
import com.vishtech.oms.exception.OrderNotFoundException;
import com.vishtech.oms.mapper.OrderMapper;
import com.vishtech.oms.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;
    private final OrderMapper orderMapper;
    private final OmsProperties omsProperties;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto request) {
        validate(request);
        OrderEntity entity= orderMapper.toOrderEntity(request);
        entity.setStatus(OrderStatus.CREATED);
        // 🔒 SAFETY: Ensure bidirectional consistency
        if (entity.getItems() != null) {
            entity.getItems().forEach(item -> item.setOrder(entity));
        }
        OrderEntity saved = repository.save(entity);
        OrderResponseDto response = orderMapper.toResponseDto(saved);
        return response;
    }

    private void validate(OrderRequestDto request) {
        if(request.getQuantity()>omsProperties.getOrder().getMaxQuantity()){
            throw new InvalidOrderException("Quantity exceeds allowed limit");
        }
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        OrderEntity entity = repository.findById(orderId).orElseThrow(()->new OrderNotFoundException(orderId));
        return orderMapper.toResponseDto(entity);
    }

    @Override
    public Page<OrderResponseDto> getOrders(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size,sort);
        return repository.findAll(pageable).map(this::toResponseDto);
    }
    private OrderResponseDto toResponseDto(OrderEntity order) {
        OrderResponseDto dto = orderMapper.toResponseDto(order);
        return dto;
    }

}
