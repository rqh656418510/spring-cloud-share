package com.java.juc.locker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的使用
 *
 * <p> 读写、写读、写写的过程是互斥的，而读读是可以共存的
 */
public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReadWriteDemo demo = new ReadWriteDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(demo.get());
            }).start();

            new Thread(() -> {
                demo.incre();
            }).start();
        }

        // 确保所有线程执行完成
        Thread.sleep(5000);

        System.out.println("finished result : " + demo.get());
    }

}

class ReadWriteDemo {

    private volatile int number = 0;

    private final ReadWriteLock locker = new ReentrantReadWriteLock();

    private final Lock readLocker = locker.readLock();

    private final Lock writeLocker = locker.writeLock();

    public int get() {
        readLocker.lock();
        try {
            return this.number;
        } finally {
            readLocker.unlock();
        }
    }

    public void incre() {
        writeLocker.lock();
        try {
            this.number++;
        } finally {
            writeLocker.unlock();
        }
    }

}
