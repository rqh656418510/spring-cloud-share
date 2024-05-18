package com.locks.study.controller;

import com.locks.study.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Lua 脚本，实现分布式锁
 */
@RestController
public class GoodsController {

    @Value("${server.port}")
    private String serverPort;

    private static final String REDIS_LOCK = "lock_buy_goods";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyGoods")
    public String buyGoods() {
        // 锁的唯一标识
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        // 加锁，并设置锁的过期时间（必须保证是原子性操作），防止因Redis宕机出现死锁
        boolean locked = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 30L, TimeUnit.SECONDS);

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
            // Lua脚本，用于防止自己加的锁被其他业务更改过（保证释放锁的操作的原子性）
            String script = "if redis.call('get', KEYS[1]) == ARGV[1]" + "then "
                + "return redis.call('del', KEYS[1])" + "else " + "  return 0 " + "end";

            // 获取连接
            Jedis jedis = RedisUtils.getJedis();

            try {
                // 执行Lua脚本
                Object evalResult =
                    jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if ("1".equals(evalResult.toString())) {
                    System.out.println("------del REDIS_LOCK_KEY success");
                } else {
                    System.out.println("------del REDIS_LOCK_KEY failed");
                }
            } finally {
                // 关闭链接
                if (null != jedis) {
                    jedis.close();
                }
            }
        }

        System.out.println(response);
        return response;
    }

}
