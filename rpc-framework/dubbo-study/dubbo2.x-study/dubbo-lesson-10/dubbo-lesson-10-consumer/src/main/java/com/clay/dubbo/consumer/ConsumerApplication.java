package com.clay.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        // 实现跟应用启动时设置 JVM 启动参数 -Denable.dubbo.admin.mock=true 的效果一样
        System.setProperty("enable.dubbo.admin.mock", "true");

        SpringApplication.run(ConsumerApplication.class);
    }

}
