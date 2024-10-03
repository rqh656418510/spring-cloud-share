package com.springcloud.study.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        // 当访问到 http://127.0.0.1:9090/jd 直接跳转到京东商城的首页
        return builder.routes()
                .route(r -> r.path("/jd")
                        .uri("http://jd.com:80/").id("jd_route")
                ).build();
    }

}
