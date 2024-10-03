package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class TestAnnotationController {

    /**
     * 资源名称
     */
    private static final String RESOURCE_NAME = "Annotation";

    /**
     * @return
     * @SentinelResource 定义资源
     * value：资源名称
     * blockHandler：限流处理的方法
     */
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "exceptionHandler")
    @GetMapping("/annotation")
    public String hello() {
        // 被保护的资源
        return "Hello Sentinel!";
    }

    /**
     * 原方法被限流的时候调用此方法
     *
     * @param e
     * @return
     */
    public String exceptionHandler(BlockException e) {
        e.printStackTrace();
        return "系统繁忙，请稍候 ...";
    }
}