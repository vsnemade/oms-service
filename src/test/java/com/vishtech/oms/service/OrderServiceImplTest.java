package com.vishtech.oms.service;

import com.vishtech.oms.domain.OrderStatus;
import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import com.vishtech.oms.entity.OrderEntity;
import com.vishtech.oms.mapper.OrderMapper;
import com.vishtech.oms.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository repository;
    @Mock
    private OrderMapper mapper;
    @InjectMocks
    private OrderServiceImpl service;
    @Test
    void createOrder_shouldSaveAndReturnResponse() {
        // given
        OrderRequestDto request = new OrderRequestDto(
                "iPhone",
                2,
                BigDecimal.valueOf(120000)
        );

        OrderEntity entity = OrderEntity.builder()
                .productName("iPhone")
                .quantity(2)
                .price(BigDecimal.valueOf(120000))
                .status(OrderStatus.CREATED)
                .build();

        OrderEntity saved = entity.toBuilder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .build();

        OrderResponseDto response = new OrderResponseDto(
                1L,
                "iPhone",
                2,
                BigDecimal.valueOf(120000),
                OrderStatus.CREATED,
                saved.getCreatedAt()
        );

        when(mapper.toOrderEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDto(saved)).thenReturn(response);

        // when
        OrderResponseDto result = service.createOrder(request);

        // then
        assertThat(result.getOrderId()).isEqualTo(1L);
        assertThat(result.getStatus()).isEqualTo(OrderStatus.CREATED);
        verify(repository).save(entity);
    }
}
