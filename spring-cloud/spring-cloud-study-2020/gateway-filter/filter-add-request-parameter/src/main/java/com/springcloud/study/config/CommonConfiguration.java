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
                .route("add_request_parameter_route", r ->
                        r.path("/addRequestParameter")
                                .filters(f -> f.addRequestParameter("book", "java"))
                                .uri("http://127.0.0.1:8080/addRequestParameter/")
                ).build();
    }

}
