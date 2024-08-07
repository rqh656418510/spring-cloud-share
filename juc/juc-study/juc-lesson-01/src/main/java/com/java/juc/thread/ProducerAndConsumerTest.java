package com.java.juc.thread;

/**
 * 线程通信之生产者消费者模式（Synchonrized 版本实现）
 *
 * <p> 一个初始值为零的变量，两个线程对其交替操作，一个线程加 1，一个线程减 1，重复 5 轮操作。
 */
public class ProducerAndConsumerTest {

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
class Clerk {

    private int product = 0;

    /**
     * 进货
     */
    public synchronized void stock() {
        try {
            // 使用 while 循环，而不是使用 if 判断，否则可能会导致虚假唤醒的现象
            while (product != 0) {
                // 等待
                this.wait();
            }
            ++product;
            System.out.println(Thread.currentThread().getName() + " : " + product);
            // 唤醒
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 卖货
     */
    public synchronized void sale() {
        try {
            // 使用 while 循环，而不是使用 if 判断，否则可能会导致虚假唤醒的现象
            while (product == 0) {
                // 等待
                this.wait();
            }
            --product;
            System.out.println(Thread.currentThread().getName() + " : " + product);
            // 唤醒
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/**
 * 生产者
 */
class Producer implements Runnable {

    private Clerk2 clerk;

    public Producer(Clerk2 clerk) {
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
class Consumer implements Runnable {

    private Clerk2 clerk;

    public Consumer(Clerk2 clerk) {
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
