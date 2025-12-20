package com.vishtech.oms.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishtech.oms.domain.OrderStatus;
import com.vishtech.oms.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderMapperTest {

    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void toResponseDto_showMapFieldsCorrectly(){
        OrderEntity entity=OrderEntity.builder()
                .id(1L)
                .productName("iPhone")
                .quantity(2)
                .price(BigDecimal.valueOf(100000))
                .status(OrderStatus.CREATED)
                .build();

        var dto = mapper.toResponseDto(entity);
        assertThat(dto.getOrderId()).isEqualTo(1L);
        assertThat(dto.getProductName()).isEqualTo("iPhone");
        assertThat(dto.getPrice()).isEqualTo(BigDecimal.valueOf(100000));
        assertThat(dto.getQuantity()).isEqualTo(2);
        assertThat(dto.getStatus()).isEqualTo(OrderStatus.CREATED);
    }
}
