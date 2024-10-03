package com.clay.retry;

import feign.RetryableException;
import feign.Retryer;

import java.util.concurrent.TimeUnit;

/**
 * 自定义 Feign 的失败重试机制
 *
 * @author clay
 * @version 1.0
 */
public class CustomRetryer implements Retryer {

    /**
     * 重试间隔时间（单位毫秒）
     */
    private final long backoff;

    /**
     * 最大重试次数
     */
    private final int maxAttempts;

    /**
     * 当前重试次数
     */
    private int attempt;

    public CustomRetryer() {
        this.backoff = 100L;
        this.maxAttempts = 3;
        this.attempt = 1;
    }

    public CustomRetryer(long backoff, int maxAttempts) {
        this.backoff = backoff;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }

        try {
            TimeUnit.MICROSECONDS.sleep(this.backoff); // 固定间隔重试
            // TimeUnit.MICROSECONDS.sleep(this.backoff * attempt); // 间隔时间线性递增
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(backoff, maxAttempts);
    }

}
