package com.locks.study.task;

import com.locks.study.service.OrderService;

import java.util.concurrent.CountDownLatch;

public class OrderTask implements Runnable {

    private final OrderService orderService;
    private final CountDownLatch countDownLatch;

    public OrderTask(OrderService orderService, CountDownLatch countDownLatch) {
        this.orderService = orderService;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.printf("线程名称：%s，订单号：%s\n", Thread.currentThread().getName(), orderService.getOrderNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
