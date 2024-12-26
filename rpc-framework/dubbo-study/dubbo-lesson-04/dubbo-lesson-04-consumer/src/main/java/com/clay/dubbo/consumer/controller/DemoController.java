package com.clay.dubbo.consumer.controller;

import com.clay.dubbo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    /**
     * 引用服务
     */
    @DubboReference
    private DemoService demoService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        String result = demoService.sayHello(name);
        log.info("===> " + result);
        return result;
    }

}
