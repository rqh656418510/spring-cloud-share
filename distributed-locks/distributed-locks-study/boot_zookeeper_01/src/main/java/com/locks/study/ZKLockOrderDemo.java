package com.locks.study;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ZooKeeper 分布式锁
 */
public class ZKLockOrderDemo {

    private static final String ZOOKEEPER_CONNECTION_STRING = "192.168.56.103:2181";
    private static final String LOCK_PATH = "/zk-lock";

    public static CuratorFramework getZKClient() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
            .connectString(ZOOKEEPER_CONNECTION_STRING)
            .retryPolicy(new ExponentialBackoffRetry(100, 1))
            .build();
        return curatorFramework;
    }

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework zkClient = getZKClient();
        zkClient.start();

        // 分布式锁
        InterProcessLock globalLock = new InterProcessMutex(zkClient, LOCK_PATH);

        ExecutorService executorService = Executors.newCachedThreadPool();
        OrderService orderService = new OrderLockService();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 9; i++) {
            OrderTask orderTask = new OrderTask(orderService, countDownLatch, globalLock);
            executorService.submit(orderTask);
        }

        countDownLatch.countDown();
        executorService.shutdown();

        TimeUnit.SECONDS.sleep(5);
        zkClient.close();
    }

}
