package com.java.juc.cas;

/**
 * 模拟 Unsafe 类的原子操作
 */
public class CompareAndSwapTest {

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int expectValue = cas.get();
                int newValue = (int) (Math.random() * 100);
                boolean result = cas.compareAndSet(expectValue, newValue);
                System.out.println(Thread.currentThread().getName() + " " + result);
            }, "T" + i).start();
        }
    }

}

class CompareAndSwap {

    private volatile int value;

    public synchronized int get() {
        return this.value;
    }

    /**
     * 比较与交换
     *
     * @param expectValue 预期值
     * @param newValue    新值
     * @return 无论是否成功交换，都返回旧值
     */
    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = this.value;
        if (oldValue == expectValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    /**
     * 比较与设置
     *
     * @param expectValue 预期值
     * @param newValue    新值
     * @return
     */
    public synchronized boolean compareAndSet(int expectValue, int newValue) {
        return expectValue == compareAndSwap(expectValue, newValue);
    }

}
