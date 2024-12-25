package com.clay.dubbo.consumer;

import com.clay.dubbo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

        DemoService demoService = context.getBean("demoService", DemoService.class);
        String result = demoService.sayHello("Dubbo");
        System.out.println("===> " + result);

        context.start();
        System.in.read();
        context.close();
    }

}
