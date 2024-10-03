package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sentinel.study.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class TestAsyncController {

    private static final String RESOURCE_NAME = "Async";

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public void hello() {
        AsyncEntry asyncEntry = null;
        try {
            // 使用限流规则
            asyncEntry = SphU.asyncEntry(RESOURCE_NAME);
            // 被保护的资源
            asyncService.hello();
        } catch (BlockException e) {
            // 被限流
            e.printStackTrace();
            System.out.println("系统繁忙，请稍后 ...");
        } finally {
            if (asyncEntry != null) {
                // 限流的出口
                asyncEntry.exit();
            }
        }
    }
}
