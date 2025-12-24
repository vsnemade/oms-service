package com.vishtech.oms.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemResponseDto {

    private String itemName;
    private Integer quantity;
    private BigDecimal price;
}
