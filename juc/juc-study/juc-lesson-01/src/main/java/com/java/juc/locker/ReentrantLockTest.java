package com.java.juc.locker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock（可重入锁）的使用
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        Tick tick = new Tick(10);
        for (int i = 0; i < 10; i++) {
            new Thread(tick).start();
        }
    }

}

class Tick implements Runnable {

    private int number;

    private Lock lockr = new ReentrantLock();

    public Tick(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        // 获取锁（会阻塞等待）
        lockr.lock();
        try {
            if (number > 0) {
                --number;
                System.out.println(Thread.currentThread().getName() + " 完成售票，余票数量为： " + number);
            }
        } finally {
            // 释放锁
            lockr.unlock();
        }
    }

}