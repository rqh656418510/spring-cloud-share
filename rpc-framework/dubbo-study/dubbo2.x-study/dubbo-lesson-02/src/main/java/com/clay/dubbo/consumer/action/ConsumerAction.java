package com.clay.dubbo.consumer.action;

import com.clay.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class ConsumerAction {

    /**
     * 使用 Dubbo 注解引用远程服务
     */
    @DubboReference
    private DemoService demoService;

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

}
