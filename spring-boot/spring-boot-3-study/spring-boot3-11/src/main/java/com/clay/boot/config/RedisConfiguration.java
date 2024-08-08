package com.clay.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author clay
 */
@Configuration
public class RedisConfiguration {

    /**
     * 设置序列化器
     */
    private void setSerializer(RedisTemplate template) {
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // Key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // Value序列化方式采用Jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash的Key采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // Hash的Value序列化方式采用Jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
    }

    /**
     * StringRedisTemplate
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        setSerializer(template);
        return template;
    }

    /**
     * RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

}
