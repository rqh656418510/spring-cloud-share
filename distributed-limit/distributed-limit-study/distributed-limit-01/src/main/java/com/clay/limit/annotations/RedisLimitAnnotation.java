package com.clay.limit.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RedisLimitAnnotation {

    /**
     * 资源的唯一 Key
     * <p> 作用：不同的接口，不同的流量控制
     */
    String key() default "";

    /**
     * 允许访问的最大次数
     */
    long limit() default 5;

    /**
     * 过期时间，也可以理解为单位时间或者滑动窗口时间
     * <p> 单位秒，默认值为 60
     */
    long expire() default 60;

    /**
     * 限流的提示语
     */
    String msg() default "接口调用过于频繁，请稍后再试";

}
