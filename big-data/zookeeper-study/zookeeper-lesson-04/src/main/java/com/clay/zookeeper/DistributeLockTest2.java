package com.clay.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.jupiter.api.Test;

public class DistributeLockTest2 {

    // ZooKeeper 连接地址（多个集群节点使用逗号分割）
    private static final String ADDRESS = "192.168.2.235:2181,192.168.2.235:2182,192.168.2.235:2183";

    // ZooKeeper 会话超时时间
    private static final int SESSION_TIMEOUT = 2000;

    // 根节点的路径
    private static final String ROOT_PATH = "/locks";

    /**
     * 获取客户端
     */
    public CuratorFramework getCuratorFramework() {
        // 重试策略
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(3000, 3);

        // 创建客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ADDRESS)
            .connectionTimeoutMs(SESSION_TIMEOUT)
            .retryPolicy(backoffRetry)
            .build();

        // 启动客户端
        client.start();

        return client;
    }

    @Test
    public void lockTest() throws InterruptedException {
        // 创建分布式锁
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(), ROOT_PATH);
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(), ROOT_PATH);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 抢占锁
                    lock1.acquire();
                    System.out.println("Thread 1 获得锁");

                    // 再次抢占锁（测试锁重入）
                    lock1.acquire();
                    System.out.println("Thread 1 再次获得锁");

                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 释放锁
                        lock1.release();
                        System.out.println("Thread 1 释放锁");

                        // 再次释放锁
                        lock1.release();
                        System.out.println("Thread 1 再次释放锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 抢占锁
                    lock2.acquire();
                    System.out.println("Thread 2 获得锁");

                    // 再次抢占锁（测试锁重入）
                    lock2.acquire();
                    System.out.println("Thread 2 再次获得锁");

                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 释放锁
                        lock2.release();
                        System.out.println("Thread 2 释放锁");

                        // 再次释放锁
                        lock2.release();
                        System.out.println("Thread 2 再次释放锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Thread.sleep(Integer.MAX_VALUE);

        // TODO 释放资源（关闭 ZooKeeper 客户端）
    }

}
