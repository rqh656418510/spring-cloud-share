package com.clay.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author clay
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}
