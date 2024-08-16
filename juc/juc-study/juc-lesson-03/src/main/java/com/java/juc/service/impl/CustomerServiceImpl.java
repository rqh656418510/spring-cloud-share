package com.java.juc.service.impl;

import com.java.juc.entity.CustomerMixInfo;
import com.java.juc.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private ThreadPoolTaskExecutor threadPool;

    @Override
    public CustomerMixInfo findCustomer() {
        CustomerMixInfo customerMixInfo = new CustomerMixInfo();
        customerMixInfo.setId(1L);

        long startTime = System.currentTimeMillis();

        // 异步调用用户远程接口
        CompletableFuture<Void> customerNameFuture = CompletableFuture.runAsync(() -> {
            customerMixInfo.setName(this.getCustomerName());
        }, threadPool);

        // 异步调用积分远程接口
        CompletableFuture<Void> scoreFuture = CompletableFuture.runAsync(() -> {
            customerMixInfo.setScore(this.getScore());
        }, threadPool);

        // 异步调用订单远程接口
        CompletableFuture<Void> orderInfoFuture = CompletableFuture.runAsync(() -> {
            customerMixInfo.setOrderInfo(this.getOrderInfo());
        }, threadPool);

        // 阻塞等待所有任务完成，allOf() 方法的应用之一是在继续执行程序之前等待完成一组独立的 CompletableFuture
        CompletableFuture.allOf(customerNameFuture, scoreFuture, orderInfoFuture).join();

        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) + " 毫秒");

        return customerMixInfo;
    }

    /**
     * 模拟调用用户远程接口
     */
    public String getCustomerName() {
        try {
            log.info("==> 调用用户远程接口");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "张三";
    }

    /**
     * 模拟调用积分远程接口
     */
    public Long getScore() {
        try {
            log.info("==> 调用积分远程接口");
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100L;
    }

    /**
     * 模拟调用订单远程接口
     */
    public String getOrderInfo() {
        try {
            log.info("==> 调用订单远程接口");
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "001 - 华为P70手机";
    }

}
