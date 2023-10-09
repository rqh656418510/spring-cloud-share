package com.clay.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author clay
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
