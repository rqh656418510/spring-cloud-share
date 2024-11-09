package com.clay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author clay
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewaySentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySentinelApplication.class, args);
    }

}