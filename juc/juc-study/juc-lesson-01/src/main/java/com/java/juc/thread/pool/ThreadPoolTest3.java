package com.java.juc.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用
 */
public class ThreadPoolTest3 {

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // 延迟执行任务（延迟 3 秒执行），任务只会执行一次
        // executorService.schedule(new MyRunnable3(), 1, TimeUnit.SECONDS);

        // 以固定频率调用指定的任务（延迟5秒后执行，每隔3秒执行一次）
        // executorService.scheduleAtFixedRate(new MyRunnable3(), 5, 3, TimeUnit.SECONDS);

        // 当任务执行完毕后，让其延迟固定时间后再次运行（延迟5秒后执行，每隔3秒执行一次）
        executorService.scheduleWithFixedDelay(new MyRunnable3(), 5, 3, TimeUnit.SECONDS);

        TimeUnit.MINUTES.sleep(1);

        // 关闭线程池
        executorService.shutdown();
    }

}

class MyRunnable3 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

}
