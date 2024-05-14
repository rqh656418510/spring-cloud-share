package com.locks.study.service;

import com.locks.study.utils.RedisUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderLockService implements OrderService {

    @Override
    public String getOrderNum() {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Long index = RedisUtils.getJedis().incr("order_number_key");
        return sdt.format(new Date()) + index;
    }

}
