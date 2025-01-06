package com.clay.dubbo.consumer.service;

import com.clay.dubbo.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("==> Hello " + name);
        return "Hello " + name;
    }

}
