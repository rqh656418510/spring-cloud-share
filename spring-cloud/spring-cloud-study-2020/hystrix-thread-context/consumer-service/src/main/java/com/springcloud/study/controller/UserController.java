package com.springcloud.study.controller;

import com.springcloud.study.config.HystrixThreadLocal;
import com.springcloud.study.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id) {
        HystrixThreadLocal.threadLocal.set("userId: " + id);
        RequestContextHolder.currentRequestAttributes().setAttribute("userId", "userId: " + id, RequestAttributes.SCOPE_REQUEST);
        logger.info("current thread: " + Thread.currentThread().getId());
        logger.info("thread local: " + HystrixThreadLocal.threadLocal.get());
        logger.info("RequestContextHolder: " + RequestContextHolder.currentRequestAttributes().getAttribute("userId", RequestAttributes.SCOPE_REQUEST));
        return userService.get(id);
    }
}
