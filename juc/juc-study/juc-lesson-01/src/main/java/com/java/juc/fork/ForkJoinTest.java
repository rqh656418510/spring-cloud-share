package com.java.juc.fork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join 框架的使用
 *
 * <p> 计算 1 + 2 + 3 + ... + 100000000L 的和
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0, 100000000L);
        // 调用 invoke 方法将任务提交到线程池，会阻塞直到任务完成
        Long sum = pool.invoke(task);
        System.out.println("sum = " + sum);
    }

}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long THURSHOLD = 100000L;   // 阀值

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        // 当任务的大小小于或等于阈值时，直接执行计算，否则将任务一分为二，递归地创建子任务并进行计算
        if (length <= THURSHOLD) {
            // 执行小任务
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;

            // 拆分大任务为小任务，并将小任务压入线程队列，等待异步执行
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();

            // 拆分大任务为小任务，并将小任务压入线程队列，等待异步执行
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();

            // 合并多个小任务的执行结果
            return left.join() + right.join();
        }
    }

}