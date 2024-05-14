package com.locks.study;

import com.locks.study.service.OrderLockService;
import com.locks.study.service.OrderService;
import com.locks.study.task.OrderTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于 Redis 实现分布式全局唯一 ID（分布式无锁实现）
 */
@SpringBootApplication
public class OrderNumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderNumberApplication.class, args);
        run();
    }

    private static void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        OrderService orderService = new OrderLockService();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 9; i++) {
            OrderTask orderTask = new OrderTask(orderService, countDownLatch);
            executorService.submit(orderTask);
        }

        countDownLatch.countDown();
        executorService.shutdown();
    }

}
