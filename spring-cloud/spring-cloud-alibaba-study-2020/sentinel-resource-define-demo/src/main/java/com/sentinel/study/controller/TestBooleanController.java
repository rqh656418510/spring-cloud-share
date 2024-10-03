package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.SphO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class TestBooleanController {

    /**
     * 资源名称
     */
    private static final String RESOURCE_NAME = "Boolean";

    @GetMapping("/boolean")
    public boolean hello() {
        // 使用限流规则
        if (SphO.entry(RESOURCE_NAME)) {
            // 被保护的资源
            try {
                System.out.println("Hello Sentinel!");
                return true;
            } finally {
                // 限流的出口
                SphO.exit();
            }
        } else {
            // 被限流
            System.out.println("系统繁忙，请稍后 ...");
            return false;
        }
    }
}
