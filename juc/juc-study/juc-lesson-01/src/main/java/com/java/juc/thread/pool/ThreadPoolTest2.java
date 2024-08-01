package com.java.juc.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池的使用
 */
public class ThreadPoolTest2 {

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future<Integer>> futureList = new ArrayList<>();

        // 提交任务
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = executorService.submit(new MyCallable());
            futureList.add(future);
        }

        // 获取线程的执行结果
        for (Future<Integer> future : futureList) {
            try {
                Integer result = future.get();
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        executorService.shutdown();
    }

}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }

}
