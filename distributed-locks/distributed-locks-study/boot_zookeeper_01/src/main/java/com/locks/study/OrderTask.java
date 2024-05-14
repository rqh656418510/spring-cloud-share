package com.locks.study;

import org.apache.curator.framework.recipes.locks.InterProcessLock;

import java.util.concurrent.CountDownLatch;

public class OrderTask implements Runnable {

    private final OrderService orderService;
    private final CountDownLatch countDownLatch;
    private final InterProcessLock globalLock;

    public OrderTask(OrderService orderService, CountDownLatch countDownLatch, InterProcessLock globalLock) {
        this.orderService = orderService;
        this.countDownLatch = countDownLatch;
        this.globalLock = globalLock;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            // 加锁
            globalLock.acquire();
            System.out.printf("线程名称：%s，订单号：%s\n", Thread.currentThread().getName(), orderService.getOrderNum());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放锁
                globalLock.release();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}
