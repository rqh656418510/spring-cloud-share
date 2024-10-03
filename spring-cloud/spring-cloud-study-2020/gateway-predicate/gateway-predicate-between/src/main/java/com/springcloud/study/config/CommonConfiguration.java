package com.springcloud.study.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
public class CommonConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        ZonedDateTime minusTime = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        ZonedDateTime plusTime = LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault());
        return builder.routes()
                .route(
                        "between_route", r -> r.between(minusTime, plusTime).uri("http://baidu.com")
                )
                .build();
    }
}
