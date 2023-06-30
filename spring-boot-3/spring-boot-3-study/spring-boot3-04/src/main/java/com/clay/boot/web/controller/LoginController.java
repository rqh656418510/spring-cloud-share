package com.clay.boot.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Slf4j
@Controller
public class LoginController {

    /**
     * 获取国际化消息的组件
     */
    @Autowired
    public MessageSource messageSource;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Locale local = request.getLocale();
        // 通过代码的方式获取国际化配置文件中指定的配置项的值
        String login = messageSource.getMessage("login", null, local);
        log.info("login: {}", login);
        return "login";
    }

}
