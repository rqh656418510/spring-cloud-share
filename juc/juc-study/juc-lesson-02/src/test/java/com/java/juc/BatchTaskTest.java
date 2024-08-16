package com.java.juc;

import com.java.juc.service.CouponServcie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BatchTaskTest {

    @Resource
    private CouponServcie couponServcie;

    @Test
    public void sendCoupons1() {
        couponServcie.batchTaskActionV1();
    }

    @Test
    public void sendCoupons2() {
        couponServcie.batchTaskActionV2();
    }

}
