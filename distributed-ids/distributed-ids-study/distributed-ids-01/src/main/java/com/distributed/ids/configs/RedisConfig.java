package com.distributed.ids.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        // 新建 RedisTemplate 对象，key 为 String 对象，value 为 Serializable（可序列化的）对象
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        // key 值使用字符串序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value 值使用 json 序列化器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 传入连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 返回 redisTemplate 对象
        return redisTemplate;
    }

}
