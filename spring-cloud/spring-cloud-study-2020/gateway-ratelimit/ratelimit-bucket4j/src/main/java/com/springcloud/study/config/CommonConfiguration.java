package com.springcloud.study.config;

import com.springcloud.study.filter.GatewayRateLimitFilterByIp;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CommonConfiguration {

    @Bean
    public RouteLocator rateLimitFilterByIp(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/rateLimit")
                        .filters(f -> f.filter(new GatewayRateLimitFilterByIp(10, 1, Duration.ofSeconds(1))))
                        .uri("http://127.0.0.1:9091/sayHello/peter/")
                        .id("ratelimit_route"))
                .build();
    }

}
