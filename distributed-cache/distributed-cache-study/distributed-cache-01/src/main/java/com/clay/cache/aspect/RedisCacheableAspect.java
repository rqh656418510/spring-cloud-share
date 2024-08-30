package com.clay.cache.aspect;

import cn.hutool.core.util.StrUtil;
import com.clay.cache.annotations.RedisCacheable;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class RedisCacheableAspect {

    @Resource
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.clay.cache.annotations.RedisCacheable)")
    public Object cacheable(ProceedingJoinPoint joinPoint) {
        Object result = null;

        try {
            // 通过反射获取目标方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // 通过反射获取目标方法上的RedisCacheable注解，如果存在，则说明需要使用缓存
            RedisCacheable RedisCacheableAnnotation = method.getAnnotation(RedisCacheable.class);

            // 获得注解上面配置的参数
            String keyPrefix = RedisCacheableAnnotation.keyPrefix();
            String matchValueSpringEL = RedisCacheableAnnotation.matchValue();

            // SpringEL 表达式的解析器
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(matchValueSpringEL);
            EvaluationContext context = new StandardEvaluationContext();

            // 获得目标方法的形参列表
            Object[] parameterValues = joinPoint.getArgs();
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++) {
                log.debug(parameterNames[i] + "=" + parameterValues[i].toString());
                context.setVariable(parameterNames[i], parameterValues[i].toString());
            }

            // 解析 SpringEL 表达式，拼接 Redis 的最终 key 形式
            String key = keyPrefix + ":" + expression.getValue(context).toString();
            if (StrUtil.isBlank(key)) {
                throw new RuntimeException("it's danger, redis key cannot be empty");
            }
            log.info("Cache key is " + key);

            // 查询 Redis 缓存
            result = redisTemplate.opsForValue().get(key);
            if (result != null) {
                // 缓存不为空，则直接返回缓存值
                log.info("Cache value found");
                return result;
            }

            // 执行目标方法
            result = joinPoint.proceed();

            // 将目标方法的执行结果写入 Redis 缓存，并设置过期时间
            if (result != null) {
                redisTemplate.opsForValue().set(key, result, 24, TimeUnit.HOURS);
            }
        } catch (Throwable e) {
            log.error("Occur Exception", e);
        }

        return result;
    }

}
