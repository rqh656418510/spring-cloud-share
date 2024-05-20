package com.java.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子包装类的使用
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(demo).start();
        }
    }

    static class AtomicDemo implements Runnable {

        // volatile 不能保证原子性，只能保证可见性
        // private volatile int number = 0;

        // 基于 CAS 解决原子性问题
        private AtomicInteger number = new AtomicInteger();

        public int updateNumber() {
            return number.getAndIncrement();
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : " + updateNumber());
        }

    }

}
