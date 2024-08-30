package com.clay.cache.controller;

import com.clay.cache.entity.User;
import com.clay.cache.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userService.add(user);
        return "success";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "success";
    }

    @PostMapping("/update")
    public String update(@RequestBody User user) {
        userService.update(user);
        return "success";
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.get(id);
    }

}
