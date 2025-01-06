package com.clay.dubbo.provider.service;

import com.clay.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 暴露服务
 */
@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
