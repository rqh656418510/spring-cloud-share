package com.java.juc.volatiles;

/**
 * synchonrized 解决内存可见性问题
 *
 * <p> 内存可见性问题是指：多个线程操作共享数据时，彼此不可见
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
            // 使用 synchronized 同步机制保证可见性（效率非常低）
            synchronized (threadDemo) {
                if (threadDemo.isFlag()) {
                    System.out.println("-----" + Thread.currentThread().getName() + " flag = " + threadDemo.isFlag());
                    break;
                }
            }
        }
    }

}
