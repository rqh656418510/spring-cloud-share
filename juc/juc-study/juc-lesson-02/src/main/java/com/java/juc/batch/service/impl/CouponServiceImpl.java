package com.java.juc.batch.service.impl;

import com.java.juc.batch.service.CouponServcie;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class CouponServiceImpl implements CouponServcie {

    // 下发优惠卷数量
    public static final Integer COUPON_NUMBER = 50;

    @Resource
    private ThreadPoolTaskExecutor threadPool;

    @Override
    public void batchTaskAction() {
        // 模拟要下发的优惠卷
        List<String> coupons = new ArrayList<>(COUPON_NUMBER);
        for (int i = 1; i <= COUPON_NUMBER; i++) {
            coupons.add("优惠卷-" + i);
        }

        long startTime = System.currentTimeMillis();

        try {
            // 创建 CountDownLatch，构造器参数为优惠卷数量
            CountDownLatch countDownLatch = new CountDownLatch(COUPON_NUMBER);

            for (String coupon : coupons) {
                try {
                    threadPool.execute(() -> {
                        System.out.println(String.format("【%s】发送成功", coupon));
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 发送一个优惠券，计数器减一
                    countDownLatch.countDown();
                }
            }

            // 阻塞当前线程，直到所有优惠券发送完成
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("任务处理完毕，总耗时: " + (endTime - startTime) + " 毫秒");
    }

}
