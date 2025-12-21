package com.vishtech.oms;

import com.vishtech.oms.config.OmsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OmsProperties.class)
public class OmsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsServiceApplication.class, args);
	}

}
