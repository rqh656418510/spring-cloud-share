package com.distributed.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MoneyFromApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyFromApplication.class);
    }

}
