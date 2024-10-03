package com.springcloud.study.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ProviderController {

    private final Logger log = LoggerFactory.getLogger(ProviderController.class);

    ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();

    @GetMapping("/addRequestHeader")
    public String addRequestHeader(HttpServletRequest request, HttpServletResponse response) {
        String head = request.getHeader("X-Request-Id");
        return "return addRequestHeader info:" + head;
    }

    @GetMapping("/addRequestParameter")
    public String addRequestParameter(HttpServletRequest request, HttpServletResponse response) {
        String parameter = request.getParameter("book");
        return "return addRequestParameter info:" + parameter;
    }

    @GetMapping("/retry")
    public String testRetryByException(@RequestParam("key") String key, @RequestParam(name = "count") int count) {
        AtomicInteger num = map.computeIfAbsent(key, s -> new AtomicInteger());
        //对请求或重试次数计数
        int i = num.incrementAndGet();
        log.warn("重试次数: " + i);
        //计数i小于重试次数2抛出异常，让Spring Cloud Gateway进行重试
        if (i < count) {
            throw new RuntimeException("Deal with failure, please try again!");
        }
        //当重试两次时候，清空计数，返回重试两次成功
        map.clear();
        return "重试" + count + "次成功！";
    }

    @GetMapping("/hystrix")
    public String index(@RequestParam("isSleep") boolean isSleep) throws InterruptedException {
        log.info("issleep is " + isSleep);
        //isSleep为true开始睡眠，睡眠时间大于Gateway中的fallback设置的时间
        if (isSleep) {
            TimeUnit.MINUTES.sleep(10);
        }
        return "No Sleep";
    }
}
