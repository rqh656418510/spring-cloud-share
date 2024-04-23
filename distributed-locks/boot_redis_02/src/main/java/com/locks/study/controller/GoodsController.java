package com.locks.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GoodsController {

    @Value("${server.port}")
    private String serverPort;

    private static final String REDIS_LOCK = "buy_goods";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyGoods")
    public String buyGoods() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        // 加锁
        boolean locked = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value);
        if (!locked) {
            return "抢锁失败";
        }

        String response = null;
        try {
            // 获取库存
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                // 更改库存
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                response = "购买商品成功，库存还剩下 " + realNumber + " 件" + "\t 服务器端口: " + serverPort;
            } else {
                response = "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
            }
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            stringRedisTemplate.delete(REDIS_LOCK);
        }
        return response;
    }

}
