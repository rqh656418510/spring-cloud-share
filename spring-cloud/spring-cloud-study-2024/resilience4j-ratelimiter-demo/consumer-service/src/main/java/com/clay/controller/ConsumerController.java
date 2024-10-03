package com.clay.controller;

import com.clay.apis.ProviderFeignApi;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 * @version 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private ProviderFeignApi providerFeignApi;

    /**
     * 该接口用于测试 Resilience4j 的限流
     * <p> @RateLimiter 注解写在需要限流的一侧
     */
    @GetMapping(value = "/consumer/ratelimit/{id}")
    @RateLimiter(name = "provider-service", fallbackMethod = "rateLimitFallback")
    public String rateLimit(@PathVariable("id") Integer id) {
        return providerFeignApi.rateLimit(id);
    }

    /**
     * 服务降级后的兜底处理方法
     */
    public String rateLimitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "RateLimiterFallback，系统繁忙，请稍后再试 /(ㄒoㄒ)/~~";
    }

}