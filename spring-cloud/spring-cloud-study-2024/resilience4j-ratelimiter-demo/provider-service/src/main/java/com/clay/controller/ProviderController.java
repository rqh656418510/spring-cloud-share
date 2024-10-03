package com.clay.controller;

import cn.hutool.core.lang.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 * @version 1.0
 */
@RestController
public class ProviderController {

    /**
     * 该接口用于测试 Resilience4j 的限流
     */
    @GetMapping(value = "/provider/ratelimit/{id}")
    public String rateLimit(@PathVariable("id") Integer id) {
        return "Hello, ratelimit! inputId :  " + id + " \t " + UUID.fastUUID();
    }

}