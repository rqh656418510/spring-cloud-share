package com.springcloud.study.controller;

import com.springcloud.study.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    public User getUser(String userName) {
        User user = new User(1L, 18, userName);
        return user;
    }

}
