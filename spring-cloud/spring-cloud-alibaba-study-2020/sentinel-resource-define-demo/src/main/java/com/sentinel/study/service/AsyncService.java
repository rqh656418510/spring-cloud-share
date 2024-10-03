package com.sentinel.study.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author clay
 */
@Service
public class AsyncService {

    /**
     * @Async 表示异步调用方法
     */
    @Async
    public void hello() {
        System.out.println("start async method ...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end async method ...");
    }
}
