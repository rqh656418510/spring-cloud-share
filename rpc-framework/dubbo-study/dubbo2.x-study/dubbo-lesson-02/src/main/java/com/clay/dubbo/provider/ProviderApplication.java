package com.clay.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@EnableDubbo(scanBasePackages = "com.clay.dubbo.provider")
@PropertySource("classpath:/provider.properties")
@ComponentScan("com.clay.dubbo.provider")
public class ProviderApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderApplication.class);
        context.start();
        System.in.read();
        context.close();
    }

}
