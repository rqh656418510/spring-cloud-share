package com.springcloud.study.config;

import com.ecwid.consul.v1.ConsulClient;
import com.springcloud.study.consul.MyConsulDiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class CommonConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public MyConsulDiscoveryClient discoveryClient(ConsulClient client, ConsulDiscoveryProperties properties) {
        return new MyConsulDiscoveryClient(client, properties);
    }
}
