package com.vishtech.oms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vishtech.oms.domain.OrderStatus;
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
    private OrderStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
