package com.vishtech.oms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "oms")
@Data
public class OmsProperties {

    private Order order = new Order();

    @Data
    public static class Order{
        private int timeoutMs;
        private int maxQuantity;
    }

}
