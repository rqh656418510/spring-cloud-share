package com.clay.dubbo.consumer.action;

import com.clay.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class ConsumerAction {

    /**
     * 使用 Dubbo 注解引用远程服务
     */
    @Reference
    private DemoService demoService;

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

}
