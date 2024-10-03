package com.springcloud.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    @HystrixCommand(fallbackMethod = "defaultUser")
    public String getUser(String userName) {
        if (userName.equals("Jim")) {
            return "this is real user";
        } else {
            throw new RuntimeException("user is not exist");
        }
    }

    public String defaultUser(String userName) {
        return "the user not exist in this system";
    }

}
