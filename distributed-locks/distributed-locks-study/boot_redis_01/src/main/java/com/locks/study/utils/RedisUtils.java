package com.locks.study.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    private static final JedisPool jedisPool;

    private static final int PORT = 6379;

    private static final String PASSWORD = "123456";

    private static final String HOST = "192.168.56.103";

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig, HOST, PORT, 100000, PASSWORD);
    }

    public static Jedis getJedis() {
        if (null != jedisPool) {
            return jedisPool.getResource();
        }
        throw new RuntimeException("Jedis is not work");
    }

}
