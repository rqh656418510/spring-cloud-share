package com.java.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 通过实现 Callable 接口来创建线程
 */
public class CallableTest {

    public static void main(String[] args) {
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();

        try {
            // 阻塞等待获取线程的执行结果
            Integer result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class MyCallable implements Callable<Integer> {

    /**
     * call 方法可以有返回值，并且可以抛出异常
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }

}
