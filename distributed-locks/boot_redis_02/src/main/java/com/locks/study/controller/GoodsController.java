package com.locks.study.controller;

import com.locks.study.locks.DistributedLock;
import com.locks.study.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class GoodsController {

    @Value("${server.port}")
    private String serverPort;

    private static final String REDIS_LOCK = "lock_buy_goods";

    @GetMapping("/buyGoods")
    public String buyGoods() {
        // 初始化锁
        Jedis jedis = RedisUtils.getJedis();
        DistributedLock lock = new DistributedLock(jedis, REDIS_LOCK, 30);

        // 加锁
        boolean locked = lock.lock();
        if (!locked) {
            // 关闭连接
            if (jedis != null) {
                jedis.close();
            }
            return "加锁失败";
        }

        String response = null;
        try {
            // 获取库存
            String stock = jedis.get("goods:001");
            int oldStock = stock == null ? 0 : Integer.parseInt(stock);
            // 判断库存是否足够
            if (oldStock > 0) {
                int newStock = oldStock - 1;
                // 更改库存
                jedis.set("goods:001", String.valueOf(newStock));
                response = "购买商品成功，库存还剩下 " + newStock + " 件" + "\t 服务器端口: " + serverPort;
            } else {
                response = "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
            }
        } catch (Exception e) {
            response = "系统出错!";
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
            // 关闭连接
            if (jedis != null) {
                jedis.close();
            }
        }

        System.out.println(response);
        return response;
    }

}
