package com.royal.reserve.bank.asset.management.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class for the Asset Management Api.
 */
@EnableCaching
@SpringBootApplication
public class AssetManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetManagementApiApplication.class, args);
    }
}
