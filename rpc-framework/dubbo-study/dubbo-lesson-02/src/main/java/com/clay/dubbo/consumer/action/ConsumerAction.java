package com.clay.dubbo.consumer.action;

import com.alibaba.dubbo.config.annotation.Reference;
import com.clay.dubbo.service.DemoService;
import org.springframework.stereotype.Component;

@Component
public class ConsumerAction {

    @Reference
    private DemoService demoService;

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

}
