package com.java.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程按序交替执行
 * <p> 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 5 遍，要求输出的结果必须按顺序显示。
 * <p> 如：ABCABCABC......依次类推。
 */
public class ABCAlternateTest2 {

    public static void main(String[] args) {
        AlterDemo2 alterDemo = new AlterDemo2();
        new Thread(alterDemo::loopA).start();
        new Thread(alterDemo::loopB).start();
        new Thread(alterDemo::loopC).start();

        // 休眠一段时间，保证三个线程都已经启动完成
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alterDemo.started();
    }

}

class AlterDemo2 {

    private boolean first = true;

    private final Lock locker = new ReentrantLock();

    private final Condition condition1 = locker.newCondition();

    private final Condition condition2 = locker.newCondition();

    private final Condition condition3 = locker.newCondition();

    /**
     * 唤醒线程一
     */
    public void started() {
        locker.lock();
        try {
            condition1.signal();
        } finally {
            locker.unlock();
        }
    }

    public void loopA() {
        locker.lock();
        try {
            while (true) {
                condition1.await();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                condition2.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void loopB() {
        locker.lock();
        try {
            while (true) {
                condition2.await();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                condition3.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void loopC() {
        locker.lock();
        try {
            while (true) {
                condition3.await();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                condition1.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

}