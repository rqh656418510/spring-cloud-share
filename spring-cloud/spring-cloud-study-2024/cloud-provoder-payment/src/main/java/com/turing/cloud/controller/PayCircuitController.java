package com.turing.cloud.controller;

import cn.hutool.core.lang.UUID;
import com.turing.cloud.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author turing
 * @version 1.0
 */
@RestController
@RequestMapping("/pay")
public class PayCircuitController {

    /**
     * 该接口用于测试服务调用方（消费者）的断路器
     */
    @GetMapping(value = "/circuit/{id}")
    public ResultData<String> circuit(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("Circuit id 不能为负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ResultData.success("Hello, circuit! inputId :  " + id + " \t " + UUID.fastUUID());
    }

    /**
     * 该接口用于测试 Resilience4j 的隔离（舱壁）
     */
    @GetMapping(value = "/bulkhead/{id}")
    public ResultData<String> bulkhead(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("Bulkhead id 不能为负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ResultData.success("Hello, bulkhead! inputId :  " + id + " \t " + UUID.fastUUID());
    }

    /**
     * 该接口用于测试 Resilience4j 的限流
     */
    @GetMapping(value = "/ratelimit/{id}")
    public ResultData<String> rateLimit(@PathVariable("id") Integer id) {
        return ResultData.success("Hello, ratelimiter! inputId :  " + id + " \t " + UUID.fastUUID());
    }

}