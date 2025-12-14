package com.vishtech.oms.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDto {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;
}
