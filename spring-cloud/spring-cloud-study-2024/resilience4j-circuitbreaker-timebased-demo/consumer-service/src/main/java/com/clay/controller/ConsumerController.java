package com.clay.controller;

import com.clay.apis.ProviderFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
     * 该接口用于测试服务调用方（消费者）的断路器
     * <p> @CircuitBreaker 注解是写在服务调用方（消费者）一侧
     */
    @GetMapping(value = "/consumer/circuit/{id}")
    @CircuitBreaker(name = "provider-service", fallbackMethod = "circuitFallback")
    public String circuitBreaker(@PathVariable("id") Integer id) {
        return providerFeignApi.circuit(id);
    }

    /**
     * 服务降级后的兜底处理方法
     */
    public String circuitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "CircuitFallback，系统繁忙，请稍后再试 /(ㄒoㄒ)/~~";
    }

}