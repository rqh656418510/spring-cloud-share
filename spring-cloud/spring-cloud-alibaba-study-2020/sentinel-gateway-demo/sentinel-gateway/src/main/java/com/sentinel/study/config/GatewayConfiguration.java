package com.sentinel.study.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * @author clay
 */
@Configuration
public class GatewayConfiguration {

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        // 设置被限流或者降级处理时的回调方法
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {

            // 被限流或者降级时处理的方法
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                return ServerResponse.status(200).syncBody("系统繁忙，请稍后 ...");
            }
        });
    }
}
