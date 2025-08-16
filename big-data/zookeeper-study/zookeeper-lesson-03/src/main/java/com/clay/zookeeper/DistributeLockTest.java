package com.clay.zookeeper;

import java.util.UUID;

public class DistributeLockTest {

    public static void main(String[] args) throws Exception {
        FairLock();
        // NonFairLock();
    }

    /**
     * 公平锁测试
     */
    public static void FairLock() throws Exception {
        DistributeFairLock lock1 = new DistributeFairLock();
        DistributeFairLock lock2 = new DistributeFairLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 抢占锁
                    lock1.lock();
                    System.out.println("Thread 1 启动，获得锁");
                    Thread.sleep(10 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock1.unlock();
                    System.out.println("Thread 1 退出，释放锁");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 抢占锁
                    lock2.lock();
                    System.out.println("Thread 2 启动，获得锁");
                    Thread.sleep(10 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock2.unlock();
                    System.out.println("Thread 2 退出，释放锁");
                }
            }
        }).start();
    }

    /**
     * 非公平锁测试
     */
    public static void NonFairLock() throws Exception {
        String lockId = UUID.randomUUID().toString();
        DistributeNonFairLock noneFailLock = DistributeNonFairLock.getInstance();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 抢占锁
                    noneFailLock.tryLock(lockId, 15 * 1000);
                    System.out.println("Thread 1 启动，获得锁");
                    Thread.sleep(10 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    noneFailLock.unlock(lockId);
                    System.out.println("Thread 1 退出，释放锁");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean locked = false;
                try {
                    // 抢占锁
                    locked = noneFailLock.tryLock(lockId, 15 * 1000);
                    if (locked) {
                        System.out.println("Thread 2 启动，获取锁成功");
                        Thread.sleep(10 * 1000);
                    } else {
                        System.out.println("Thread 2 启动，获取锁失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    if (locked) {
                        noneFailLock.unlock(lockId);
                        System.out.println("Thread 2 退出，释放锁");
                    }
                }
            }
        }).start();
    }

}
