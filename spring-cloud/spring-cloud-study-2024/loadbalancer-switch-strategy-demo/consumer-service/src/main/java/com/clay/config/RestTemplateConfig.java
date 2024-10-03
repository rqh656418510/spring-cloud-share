package com.clay.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 使用 @LoadBalancerClient 注解声明某个服务（如 provider-service）使用随机策略，服务名称大小写敏感
 *
 * @author clay
 * @version 1.0
 */
@Configuration
@LoadBalancerClient(value = "provider-service", configuration = RandomLoadBalancerConfiguration.class)
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
