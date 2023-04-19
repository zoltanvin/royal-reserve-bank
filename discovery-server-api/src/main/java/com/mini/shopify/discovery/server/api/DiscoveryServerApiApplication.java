package com.mini.shopify.discovery.server.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApiApplication.class, args);
    }
}