package com.vishtech.oms.specification;

import com.vishtech.oms.entity.OrderEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class OrderSpecification {

    public static Specification<OrderEntity> minPrice(BigDecimal price) {
        return (root, query, cb) ->
                price == null ? null : cb.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<OrderEntity> productName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.equal(root.get("productName"), name);
    }
}
