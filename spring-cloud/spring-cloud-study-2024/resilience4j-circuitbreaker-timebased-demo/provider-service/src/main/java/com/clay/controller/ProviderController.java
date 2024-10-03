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
     * 该接口用于测试服务调用方（消费者）的断路器
     */
    @GetMapping(value = "/provider/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id) {
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
        return "Hello, circuit! inputId :  " + id + " \t " + UUID.fastUUID();
    }

}