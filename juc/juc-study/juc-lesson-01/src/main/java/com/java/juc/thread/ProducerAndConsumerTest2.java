package com.java.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信之生产者消费者模式（Condition 版本实现）
 *
 * <p> 一个初始值为零的变量，两个线程对其交替操作，一个线程加 1，一个线程减 1，重复 5 轮操作。
 */
public class ProducerAndConsumerTest2 {

    public static void main(String[] args) {
        Clerk2 clerk = new Clerk2();
        Producer2 producer = new Producer2(clerk);
        Consumer2 consumer = new Consumer2(clerk);

        new Thread(producer, "生产者 A").start();
        new Thread(consumer, "消费者 B").start();
    }

}

/**
 * 店员
 */
class Clerk2 {

    private int product = 0;

    private Lock locker = new ReentrantLock();

    private Condition condition = locker.newCondition();

    /**
     * 进货
     */
    public void stock() {
        locker.lock();
        try {
            // 使用 while 循环，而不是使用 if 判断，否则可能会导致虚假唤醒的现象
            while (product != 0) {
                // 等待
                condition.await();
            }
            ++product;
            System.out.println(Thread.currentThread().getName() + " : " + product);
            // 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    /**
     * 卖货
     */
    public void sale() {
        locker.lock();
        try {
            // 使用 while 循环，而不是使用 if 判断，否则可能会导致虚假唤醒的现象
            while (product == 0) {
                // 等待
                condition.await();
            }
            --product;
            System.out.println(Thread.currentThread().getName() + " : " + product);
            // 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

}

/**
 * 生产者
 */
class Producer2 implements Runnable {

    private Clerk2 clerk;

    public Producer2(Clerk2 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        // 进货
        for (int i = 0; i < 5; i++) {
            this.clerk.stock();
        }
    }

}

/**
 * 消费者
 */
class Consumer2 implements Runnable {

    private Clerk2 clerk;

    public Consumer2(Clerk2 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        // 买货
        for (int i = 0; i < 5; i++) {
            this.clerk.sale();
        }
    }

}
