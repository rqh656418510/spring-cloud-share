package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class TestController {

    /**
     * 资源名称
     */
    private static final String RESOURCE_NAME = "Hello";

    @GetMapping("/hello")
    public String hello() {
        // 使用限流规则
        try (Entry entry = SphU.entry(RESOURCE_NAME)) {
            // 被保护的资源
            return "Hello Sentinel!";
        } catch (Exception e) {
            // 被限流
            e.printStackTrace();
            return "系统繁忙，请稍后 ...";
        }
    }
}