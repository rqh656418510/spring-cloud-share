package com.locks.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class GoodsController {

    @Value("${server.port}")
    private String serverPort;

    private static final String REDIS_LOCK = "buy_goods";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyGoods")
    public String buyGoods() {
        // 锁的唯一标识
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        // 加锁并设置锁的过期时间（必须保证是原子性操作），防止因Redis宕机出现死锁
        boolean locked = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);

        if (!locked) {
            return "抢锁失败";
        }

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

        }

        System.out.println(response);
        return response;
    }

}
