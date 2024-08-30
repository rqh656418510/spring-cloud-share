package com.clay.cache.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis 自定义缓存注解
 * <p> 将方法运行的结果进行缓存，在缓存时效内再次调用该方法时不会调用方法本身，而是直接从缓存获取结果并返回给调用方
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheable {

    /**
     * 键的前缀
     */
    String keyPrefix();

    /**
     * SpringEL 表达式解析占位符对应的匹配 value 值
     */
    String matchValue();

}
