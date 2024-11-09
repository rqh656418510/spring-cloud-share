package com.turing.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@Slf4j
@RestController
public class RateLimitController {

    @GetMapping("/rateLimit/byUrl")
    public String byUrl() {
        return "按 URL 地址进行限流测试";
    }

    /**
     * 演示 @SentinelResource 注解的使用
     */
    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "bySentinelResource", blockHandler = "handlerException")
    public String byResource() {
        return "按资源名称进行限流测试";
    }

    public String handlerException(BlockException exception) {
        return "目前访问人数较多，请稍后再试!";
    }

    /**
     * 演示 @SentinelResource 注解的 blockHandler 和 fallback 属性同时使用
     */
    @GetMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource", blockHandler = "doActionBlockHandler", fallback = "doActionFallback")
    public String doAction(@PathVariable("p1") Integer p1) {
        if (p1 == 0) {
            throw new RuntimeException("非法参数异常");
        }
        return "success";
    }

    public String doActionBlockHandler(@PathVariable("p1") Integer p1, BlockException e) {
        log.error("接口触发了限流: {}", e);
        return "目前访问人数较多，请稍后再试!";
    }

    public String doActionFallback(@PathVariable("p1") Integer p1, Throwable e) {
        log.error("程序抛出了异常: {}", e);
        return "服务出错啦，请稍后再试!";
    }

    /**
     * 演示热点规则的使用
     */
    @GetMapping("/rateLimit/hotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "hotKeyHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "success";
    }

    public String hotKeyHandler(String p1, String p2, BlockException exception) {
        return "hot key rate limit";
    }

}
