package com.clay.controller;

import com.clay.apis.ProviderFeignApi;
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
     * 该接口用于测试 Micrometer 链路监控
     */
    @GetMapping("/consumer/micrometer/{id}")
    String micrometer(@PathVariable("id") Integer id) {
        return providerFeignApi.micrometer(id);
    }

}