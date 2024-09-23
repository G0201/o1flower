package com.example.flower;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.flower.mapper")
@SpringBootApplication
public class FlowerDeliveryPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowerDeliveryPlatformApplication.class, args);
    }
}
