package com.clay.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("/count")
    public String count() {
        Long total = stringRedisTemplate.opsForValue().increment("total");
        return "Access " + total + " times";
    }

    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return "success";
    }

}
