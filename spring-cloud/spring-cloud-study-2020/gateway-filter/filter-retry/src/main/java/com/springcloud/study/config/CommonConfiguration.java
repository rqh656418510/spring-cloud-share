package com.springcloud.study.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class CommonConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("retry_route", r -> r.path("/test/retry")
                        .filters(f -> f.retry(config -> config.setRetries(2)
                                .setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
                        .uri("http://127.0.0.1:8080/retry?key=abc&count=2"))
                .build();
    }

}
