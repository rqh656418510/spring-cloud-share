package com.clay.dubbo.provider.service;

import com.clay.dubbo.service.DemoService;

public class DemoServiceImpl2 implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("===> v2.0");
        return "Hello " + name;
    }

}
