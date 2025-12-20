package com.vishtech.oms.repository;

import com.vishtech.oms.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByProductName(String productName);

    List<OrderEntity> findByPriceGreaterThan(BigDecimal price);

    List<OrderEntity> findByProductNameAndQuantity(String productName, Integer quantity);

    List<OrderEntity> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    Page<OrderEntity> findAll(Pageable pageable);

    @Query("""
    SELECT o FROM OrderEntity o
    WHERE o.price >= :minPrice
    AND o.quantity >= :minQty
""")
    List<OrderEntity> findExpensiveOrders(
            @Param("minPrice") BigDecimal minPrice,
            @Param("minQty") Integer minQty
    );

}
