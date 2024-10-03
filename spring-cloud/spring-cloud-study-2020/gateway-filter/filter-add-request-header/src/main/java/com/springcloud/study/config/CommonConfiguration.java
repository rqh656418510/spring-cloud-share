package com.springcloud.study.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("add_request_header_route", r ->
                        r.path("/addRequestHeader")
                                .filters(f -> f.addRequestHeader("X-Request-Id", "Peter"))
                                .uri("http://127.0.0.1:8080/addRequestHeader/")
                ).build();
    }

}
