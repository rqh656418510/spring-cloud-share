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
                .route("rewrite_path_route", r ->
                        r.path("/foo/**")
                                .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/$\\{segment}"))
                                .uri("http://www.baidu.com")
                ).build();
    }

}
