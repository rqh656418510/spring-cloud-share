package com.clay.zookeeper;

public class DistributeLockTest {

    public static void main(String[] args) throws Exception {
        DistributeLock lock1 = new DistributeLock();
        DistributeLock lock2 = new DistributeLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取锁
                    lock1.lock();
                    System.out.println("Thread 1 启动，获得锁");
                    Thread.sleep(10000);
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
                    // 获取锁
                    lock2.lock();
                    System.out.println("Thread 2 启动，获得锁");
                    Thread.sleep(10000);
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

}
