package com.vishtech.oms.health;


import jakarta.annotation.Nullable;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceHealthIndicator implements HealthIndicator {
    @Override
    public @Nullable Health health() {
        boolean serviceUp = true; // simulate check

        if (serviceUp) {
            return Health.up()
                    .withDetail("order-service", "Available")
                    .build();
        }

        return Health.down()
                .withDetail("order-service", "Unavailable")
                .build();
    }
}
