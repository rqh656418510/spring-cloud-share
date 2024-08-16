package com.java.juc.util;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * 批量发送工具类
 */
public class TaskBatchSendUtils {

    public static <T> void send(List<T> taskList, Executor threadPool, Consumer<? super T> consumer)
        throws InterruptedException {
        if (taskList == null || taskList.size() == 0) {
            return;
        }

        if (Objects.isNull(consumer)) {
            return;
        }

        CountDownLatch countDownLatch = new CountDownLatch(taskList.size());

        for (T task : taskList) {
            threadPool.execute(() ->
            {
                try {
                    consumer.accept(task);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
    }

    public static void emailTask(String task) {
        System.out.println(String.format("【%s】下发邮件成功", task));
    }

    public static void textMessageTask(String task) {
        System.out.println(String.format("【%s】下发短信成功", task));
    }

    public static void couponTask(String task) {
        System.out.println(String.format("【%s】下发优惠卷成功", task));
    }

}