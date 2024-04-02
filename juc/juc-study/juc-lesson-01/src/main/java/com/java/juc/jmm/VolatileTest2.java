package com.java.juc.jmm;

/**
 * synchonrized 解决内存可见性问题
 */
class ThreadDemo2 implements Runnable {

    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(Thread.currentThread().getName() + " flag = " + flag);
    }

}

public class VolatileTest2 {

    public static void main(String[] args) {
        ThreadDemo1 threadDemo = new ThreadDemo1();

        // 启动一个线程，更改共享数据
        new Thread(threadDemo, "T1").start();

        // 主线程读取共享数据
        while (true) {
            // 使用 synchronized 同步机制
            synchronized (threadDemo) {
                if (threadDemo.isFlag()) {
                    System.out.println(Thread.currentThread().getName() + " flag = " + threadDemo.isFlag());
                    break;
                }
            }
        }
    }

}
