package com.locks.study;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class OrderLockService implements OrderService {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public String getOrderNum() {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdt.format(new Date()) + atomicLong.incrementAndGet();
    }

}
