package com.clay.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author clay
 */
@Controller
public class LoginController {

    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}
