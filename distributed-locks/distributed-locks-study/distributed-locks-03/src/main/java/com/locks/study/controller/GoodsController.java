package com.locks.study.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于 Redis + Redisson 框架实现分布式锁
 */
@RestController
public class GoodsController {

    @Value("${server.port}")
    private String serverPort;

    private static final String REDIS_LOCK = "lock_buy_goods";

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyGoods")
    public String buyGoods() {
        RLock rlock = redissonClient.getLock(REDIS_LOCK);
        // 加分布式锁
        rlock.lock();

        String response = null;
        try {
            // 获取库存
            String stock = stringRedisTemplate.opsForValue().get("goods:001");
            int oldStock = stock == null ? 0 : Integer.parseInt(stock);
            // 判断库存是否足够
            if (oldStock > 0) {
                int newStock = oldStock - 1;
                // 更改库存
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(newStock));
                response = "购买商品成功，库存还剩下 " + newStock + " 件" + "\t 服务器端口: " + serverPort;
            } else {
                response = "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
            }
        } catch (Exception e) {
            response = "系统出错!";
            e.printStackTrace();
        } finally {
            // 释放分布式锁
            if (rlock.isLocked() && rlock.isHeldByCurrentThread()) {
                rlock.unlock();
            }
        }

        System.out.println(response);
        return response;
    }

}
