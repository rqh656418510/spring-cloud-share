package com.clay.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author clay
 * @version 1.0
 */
@Configuration
public class GatewayConfig {

    /**
     * WebFlux 静态资源配置
     *
     * @return serverResponse
     */
    @Bean
    RouterFunction<ServerResponse> staticResourceRouter() {
        return RouterFunctions.resources("/webjars/**", new ClassPathResource("webjars/"));
    }
}