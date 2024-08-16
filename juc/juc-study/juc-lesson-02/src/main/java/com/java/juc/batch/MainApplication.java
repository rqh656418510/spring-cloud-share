package com.java.juc.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class MainApplication {

    @Resource
    private ThreadPoolTaskExecutor threadPool;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @PostConstruct
    public void getThreadPoolConfig() {
        System.out.println("******* CorePoolSize: " + threadPool.getCorePoolSize());
        System.out.println("******* MaxPoolSize: " + threadPool.getMaxPoolSize());
        System.out.println("******* KeepAliveSeconds: " + threadPool.getKeepAliveSeconds());
    }

}
