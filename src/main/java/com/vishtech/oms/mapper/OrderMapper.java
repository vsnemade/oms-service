package com.vishtech.oms.mapper;


import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import com.vishtech.oms.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "id", target = "orderId")
    @Mapping(target = "status", constant = "CREATED")
    OrderResponseDto toResponseDto(OrderEntity orderEntity);

    OrderEntity toOrderEntity(OrderRequestDto orderRequestDto);
}
