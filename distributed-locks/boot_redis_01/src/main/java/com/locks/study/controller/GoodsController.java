package com.locks.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyGoods")
    public String buyGoods() {
        String response = null;
        String result = stringRedisTemplate.opsForValue().get("goods:001");
        int goodsNumber = result == null ? 0 : Integer.parseInt(result);
        if (goodsNumber > 0) {
            int realNumber = goodsNumber - 1;
            stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
            response = "购买商品成功，库存还剩下 " + realNumber + " 件" + "\t 服务器端口: " + serverPort;
        } else {
            response = "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
        }
        System.out.println(response);
        return response;
    }

}
