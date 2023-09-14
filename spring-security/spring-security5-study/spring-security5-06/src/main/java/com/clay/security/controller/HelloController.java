package com.clay.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Security";
    }

    @RequestMapping("/goodbye")
    public String goodbye() {
        return "Goodbye Spring Security";
    }

    @RequestMapping("/goodnight")
    public String goodnight() {
        return "Goodnight Spring Security";
    }

}
