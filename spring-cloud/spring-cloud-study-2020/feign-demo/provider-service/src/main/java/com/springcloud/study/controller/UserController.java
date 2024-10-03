package com.springcloud.study.controller;

import com.springcloud.study.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public User addUser(User user) {
        System.out.println("==> add: " + user.getId() + '-' + user.getName() + "-" + user.getAge());
        return user;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) {
        System.out.println("==> update: " + user.getId() + '-' + user.getName() + "-" + user.getAge());
        return user;
    }

}
