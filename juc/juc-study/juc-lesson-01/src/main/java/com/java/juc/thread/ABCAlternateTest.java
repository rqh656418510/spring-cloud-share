package com.java.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程按序交替执行
 * <p> 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 5 遍，要求输出的结果必须按顺序显示。
 * <p> 如：ABCABCABC......依次类推。
 */
public class ABCAlternateTest {

    public static void main(String[] args) {
        AlterDemo alterDemo = new AlterDemo();

        new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                alterDemo.loopA();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                alterDemo.loopB();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                alterDemo.loopC();
            }
        }).start();
    }

}

class AlterDemo {

    // 标记当前由哪个线程执行
    private int number = 1;

    private final Lock locker = new ReentrantLock();

    private final Condition condition1 = locker.newCondition();

    private final Condition condition2 = locker.newCondition();

    private final Condition condition3 = locker.newCondition();

    public void loopA() {
        locker.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            System.out.println("A");
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void loopB() {
        locker.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.println("B");
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void loopC() {
        locker.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            System.out.println("C");
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

}