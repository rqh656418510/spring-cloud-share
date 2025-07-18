package com.clay.dubbo.consumer.controller;

import com.clay.dubbo.domain.User;
import com.clay.dubbo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    /**
     * 引用 Dubbo 服务
     */
    @DubboReference
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("/addUser")
    public Boolean addUser(@RequestBody User user) {
        return userService.add(user);
    }

}
