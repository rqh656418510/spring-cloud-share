package com.clay.demo.zuul.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时刷新网关API路由规则
 */
@Component
@Configuration
@EnableScheduling
public class RefreshApiRouteTask {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RouteLocator routeLocator;

    @Scheduled(fixedRate = 5000)
    private void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }

}