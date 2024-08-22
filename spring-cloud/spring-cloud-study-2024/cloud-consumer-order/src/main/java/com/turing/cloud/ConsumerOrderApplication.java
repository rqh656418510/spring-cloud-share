package com.turing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author turing
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApplication.class, args);
    }

}