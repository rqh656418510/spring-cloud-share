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
     * 该接口用于测试 Micrometer 链路监控
     */
    @GetMapping("/provider/micrometer/{id}")
    public String micrometer(@PathVariable("id") Integer id) {
        return "Hello, welcome to Micrometer, inputId : " + id + "\t    " + UUID.fastUUID();
    }

}