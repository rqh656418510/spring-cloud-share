package com.turing.cloud.controller;

import com.turing.cloud.api.PayFeignApi;
import com.turing.cloud.resp.ResultData;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@RestController
@RequestMapping("/ordder")
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;

    /**
     * 该接口用于测试服务调用方（消费者）的断路器
     * <p> @CircuitBreaker 注解是写在服务调用方（消费者）一侧
     */
    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "circuitFallback")
    public ResultData<String> circuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.circuit(id);
    }

    /**
     * 服务降级后的兜底处理方法
     */
    public ResultData<String> circuitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return ResultData.success("CircuitFallback，系统繁忙，请稍后再试 /(ㄒoㄒ)/~~");
    }

    /**
     * 该接口用于测试 Resilience4j 的隔离（舱壁）
     * <p> @Bulkhead 注解写在需要限制并发访问的一侧
     */
    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public ResultData<String> bulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.bulkhead(id);
    }

    /**
     * 服务降级后的兜底处理方法
     */
    public ResultData<String> bulkheadFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return ResultData.success("BulkheadFallback，系统繁忙，请稍后再试 /(ㄒoㄒ)/~~");
    }

}
