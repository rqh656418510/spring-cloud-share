package com.clay.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author turing
 * @version 1.0
 */
@Configuration
public class GatewayConfiguration {

    @PostConstruct
    public void doInit() {
        initGatewayBlockHandler();
    }

    /**
     * 自定义触发 Sentinel 控制规则（如流控规则、熔断规则等）后的处理逻辑
     */
    public void initGatewayBlockHandler() {
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable ex) {
                Map<String, String> map = new HashMap<>();
                map.put("errorCode", String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
                map.put("errorMessage", "系统繁忙，请稍后再试!");

                return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS).contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(map));
            }
        });
    }

}
