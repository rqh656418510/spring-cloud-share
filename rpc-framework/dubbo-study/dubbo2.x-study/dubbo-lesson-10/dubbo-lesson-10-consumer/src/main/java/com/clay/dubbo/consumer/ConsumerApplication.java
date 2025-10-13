package com.clay.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        // 启用 Dubbo Admin Mock 功能，必须在 Dubbo 启动前设置，实现效果跟应用启动时设置 JVM 启动参数 -Denable.dubbo.admin.mock=true 一样
        System.setProperty("enable.dubbo.admin.mock", "true");

        SpringApplication.run(ConsumerApplication.class);
    }

}
