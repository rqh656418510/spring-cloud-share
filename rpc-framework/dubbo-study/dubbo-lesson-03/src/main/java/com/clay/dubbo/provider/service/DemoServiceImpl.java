package com.clay.dubbo.provider.service;

import com.clay.dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
