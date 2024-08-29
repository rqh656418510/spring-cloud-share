package com.clay.log.tracing.aspect;

import com.clay.log.tracing.annotations.MethodExporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class MethodExporterAspect {

    /**
     * 环绕通知，使用了MethodExporter注解将会触发Around业务逻辑
     */
    @Around("@annotation(com.clay.log.tracing.annotations.MethodExporter)")
    public Object methodExporter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 方法的返回结果
        Object resultValue = null;

        log.info("----- @Around before");
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        resultValue = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // 获得重写后的方法名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        // 确定方法名后，获得该方法上面配置的注解标签
        MethodExporter methodExporterAnnotation = method.getAnnotation(MethodExporter.class);

        if (methodExporterAnnotation != null) {
            // 获得方法里面的形参信息
            StringBuilder jsonParam = new StringBuilder();
            Object[] parameterValues = proceedingJoinPoint.getArgs();
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++) {
                jsonParam.append(parameterNames[i] + " = " + parameterValues[i].toString() + "; ");
            }
            // 将返回结果序列化
            String jsonResult = null;
            if (resultValue != null) {
                jsonResult = new ObjectMapper().writeValueAsString(resultValue);
            } else {
                jsonResult = "null";
            }

            log.info("\n方法分析上报中 " +
                "\n类名方法名: " + proceedingJoinPoint.getTarget().getClass().getName() + "." +
                proceedingJoinPoint.getSignature().getName() + "()" +
                "\n执行耗时: " + costTime + "毫秒" +
                "\n输入参数: " + jsonParam + "" +
                "\n返回结果: " + jsonResult + "" +
                "\n方法分析上报结束"
            );
            log.info("----- @Around after");
        }

        return resultValue;
    }

}
