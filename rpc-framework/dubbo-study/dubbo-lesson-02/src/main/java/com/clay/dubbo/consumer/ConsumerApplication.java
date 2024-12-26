package com.clay.dubbo.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.clay.dubbo.consumer.action.ConsumerAction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@EnableDubbo(scanBasePackages = "com.clay.dubbo.consumer")
@PropertySource("classpath:/consumer.properties")
@ComponentScan("com.clay.dubbo.consumer")
public class ConsumerApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerApplication.class);

        ConsumerAction consumerAction = context.getBean(ConsumerAction.class);
        String result = consumerAction.doSayHello("Dubbo");
        System.out.println("===> " + result);

        context.start();
        System.in.read();
        context.close();
    }

}
