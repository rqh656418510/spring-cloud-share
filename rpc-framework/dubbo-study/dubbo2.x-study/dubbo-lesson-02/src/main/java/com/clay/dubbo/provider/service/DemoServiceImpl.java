package com.clay.dubbo.provider.service;

import com.clay.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 使用 Dubbo 注解暴露服务
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
