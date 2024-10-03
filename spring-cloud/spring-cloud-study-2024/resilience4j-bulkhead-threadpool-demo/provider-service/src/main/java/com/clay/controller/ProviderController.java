package com.clay.controller;

import cn.hutool.core.lang.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author clay
 * @version 1.0
 */
@RestController
public class ProviderController {

    /**
     * 该接口用于测试 Resilience4j 的隔离（舱壁）
     */
    @GetMapping(value = "/provider/bulkhead/{id}")
    public String bulkhead(@PathVariable("id") Integer id) {
        // 模拟业务处理出错
        if (id == -4) {
            throw new RuntimeException("Bulkhead id 不能为负数");
        }
        // 模拟业务长时间处理
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, bulkhead! inputId :  " + id + " \t " + UUID.fastUUID();
    }

}