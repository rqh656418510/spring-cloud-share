package com.clay.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author clay
 */
@Controller
public class HelloController {

    /**
     * 跳转Hello页面
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "/hello";
    }

}
