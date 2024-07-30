package com.java.juc.locker;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch (闭锁)的使用
 */
public class CountDownLatchTest {

    /**
     * 计算多个线程执行的总耗时
     */
    public static void main(String[] args) throws Exception {
        final int size = 5;
        CountDownLatch countDownLatch = new CountDownLatch(size);

        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            new Thread(new LatchDemo(countDownLatch)).start();
        }

        // 阻塞等待，直到计数器为零
        countDownLatch.await();

        long end = System.currentTimeMillis();
        System.out.println("total time : " + (end - start) + "ms");
    }

}

class LatchDemo implements Runnable {

    private CountDownLatch countDownLatch;

    public LatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            // 模拟业务耗时
            Thread.sleep(new Random().nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 计数器减一
        countDownLatch.countDown();
    }

}
