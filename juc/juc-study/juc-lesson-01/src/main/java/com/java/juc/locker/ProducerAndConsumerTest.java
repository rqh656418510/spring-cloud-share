package com.java.juc.locker;

/**
 * 线程通信之生产者消费者模式
 *
 * <p> 一个初始值为零的变量，两个线程对其交替操作，一个线程加 1，一个线程减 1，重复 5 轮操作。
 */
public class ProducerAndConsumerTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);

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

    private Clerk clerk;

    public Producer(Clerk clerk) {
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

    private Clerk clerk;

    public Consumer(Clerk clerk) {
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
