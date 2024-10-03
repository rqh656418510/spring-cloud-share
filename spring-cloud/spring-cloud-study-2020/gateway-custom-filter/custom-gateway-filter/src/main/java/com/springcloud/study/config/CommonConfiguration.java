package com.springcloud.study.config;

import com.springcloud.study.filter.CustomGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public RouteLocator customGatewayFilter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/custom/gateway/filter")
                        .filters(f -> f.filter(new CustomGatewayFilter()))
                        .uri("http://127.0.0.1:9090/provider/sayHello/Jim/")
                        .order(0)
                        .id("custom-gateway-filter")
                )
                .build();
    }
}
