package com.clay.limit.aspect;

import cn.hutool.core.util.StrUtil;
import com.clay.limit.annotations.RedisLimitAnnotation;
import com.clay.limit.exception.RedisLimitException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

@Slf4j
@Aspect
@Component
public class RedisLimitAspect {

    Object result = null;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<Long> redisLuaScript;

    @PostConstruct
    public void init() {
        // 加载 Lua 脚本
        redisLuaScript = new DefaultRedisScript<>();
        redisLuaScript.setResultType(Long.class);
        redisLuaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("RateLimiter.lua")));
    }

    @Around("@annotation(com.clay.limit.annotations.RedisLimitAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("---------@Around before");

        // 获取目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取目标方法上的RedisLimitAnnotation注解，如果存在，则说明需要限流
        RedisLimitAnnotation redisLimitAnnotation = method.getAnnotation(RedisLimitAnnotation.class);

        if (redisLimitAnnotation != null) {
            // 获取Redis中的key
            String key = redisLimitAnnotation.key();
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();

            if (StrUtil.isBlank(key)) {
                throw new RedisLimitException("it's danger, limit key cannot be empty");
            }

            String limitInfo = key + "\t" + className + "." + methodName + "()";
            log.info(limitInfo);

            long limit = redisLimitAnnotation.limit();
            long expire = redisLimitAnnotation.expire();
            List<String> keys = Collections.singletonList(key);

            // 执行 Lua 脚本
            Long count =
                stringRedisTemplate.execute(redisLuaScript, keys, String.valueOf(limit), String.valueOf(expire));

            if (count != null && count == -1) {
                log.warn("启动限流功能, Key 为 " + key);
                return redisLimitAnnotation.msg();
            }

            log.info("Access try count is " + count + ", limit key is " + key);
        }

        try {
            // 执行目标方法
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        System.out.println("---------@Around after");

        return result;
    }

}
