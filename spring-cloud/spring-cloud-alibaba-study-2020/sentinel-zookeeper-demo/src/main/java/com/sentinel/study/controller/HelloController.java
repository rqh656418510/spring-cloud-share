package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 */
@RestController
public class HelloController {

    private final static Logger LOG = LoggerFactory.getLogger(HelloController.class);

    /**
     * 资源名称
     */
    public static final String RESOURCE_NAME = "Hello";

    /**
     * @return
     * @SentinelResource 定义资源
     * value：资源名称
     * blockHandler：限流处理的方法
     */
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "exceptionHandler")
    @GetMapping("/hello")
    public String hello() {
        return "Hello Sentinel!";
    }

    /**
     * 原方法被限流的时候调用此方法
     *
     * @param e
     * @return
     */
    public String exceptionHandler(BlockException e) {
        LOG.info("系统繁忙，请稍候 ...");
        return "系统繁忙，请稍候 ...";
    }
}
