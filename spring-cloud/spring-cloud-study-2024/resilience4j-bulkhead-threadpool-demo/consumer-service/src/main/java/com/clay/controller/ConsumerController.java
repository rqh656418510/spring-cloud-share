package com.clay.controller;

import com.clay.apis.ProviderFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author clay
 * @version 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private ProviderFeignApi providerFeignApi;

    /**
     * 该接口用于测试 Resilience4j 的隔离（舱壁）
     * <p> @Bulkhead 注解写在需要限制并发访问的一侧
     */
    @GetMapping(value = "/consumer/bulkhead/{id}")
    @Bulkhead(name = "provider-service", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> bulkhead(@PathVariable("id") Integer id) {
        return CompletableFuture.supplyAsync(() -> providerFeignApi.bulkhead(id));
    }

    /**
     * 服务降级后的兜底处理方法
     */
    public CompletableFuture<String> bulkheadFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return CompletableFuture.supplyAsync(() -> "ThreadPoolBulkhead Fallback，系统繁忙，请稍后再试 /(ㄒoㄒ)/~~");
    }

}