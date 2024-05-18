package com.distributed.ids;

import com.distributed.ids.service.OrderService;
import com.distributed.ids.task.OrderTask;
import com.distributed.ids.service.OrderLockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于 Redis 实现分布式全局唯一ID（分布式无锁实现）
 */
@SpringBootApplication
public class OrderNumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderNumberApplication.class, args);
        run();
    }

    private static void run() {
        // 使用线程池，模拟多个线程竞争获取订单ID
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
