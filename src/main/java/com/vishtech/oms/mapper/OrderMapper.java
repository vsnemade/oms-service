package com.vishtech.oms.mapper;


import com.vishtech.oms.dto.OrderItemRequestDto;
import com.vishtech.oms.dto.OrderItemResponseDto;
import com.vishtech.oms.dto.OrderRequestDto;
import com.vishtech.oms.dto.OrderResponseDto;
import com.vishtech.oms.entity.OrderEntity;
import com.vishtech.oms.entity.OrderItemEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "items", target = "items")
    OrderResponseDto toResponseDto(OrderEntity orderEntity);

    List<OrderItemResponseDto> toItemResponseDtos(List<OrderItemEntity> items);

    OrderItemResponseDto toItemResponseDto(OrderItemEntity item);

    OrderEntity toOrderEntity(OrderRequestDto dto);

    List<OrderItemEntity> toItemEntities(List<OrderItemRequestDto> items);

    OrderItemEntity toItemEntity(OrderItemRequestDto dto);

    @AfterMapping
    default void linkItems(@MappingTarget OrderEntity orderEntity) {
        if (orderEntity.getItems() != null) {
            orderEntity.getItems()
                    .forEach(item -> item.setOrder(orderEntity));
        }
    }
}
