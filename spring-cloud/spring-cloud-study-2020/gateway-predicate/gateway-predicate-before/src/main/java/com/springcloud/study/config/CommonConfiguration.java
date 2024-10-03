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
        // 生成比当前时间晚一个小时的UTC时间
        ZonedDateTime plusTime = LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault());
        return builder.routes()
                .route(
                        "before_route", r -> r.before(plusTime).uri("http://baidu.com")
                )
                .build();
    }
}
