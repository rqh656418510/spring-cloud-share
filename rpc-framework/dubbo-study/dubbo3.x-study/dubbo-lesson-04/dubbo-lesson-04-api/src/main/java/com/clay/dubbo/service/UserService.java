package com.clay.dubbo.service;

import com.clay.dubbo.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public interface UserService {

    @PostMapping("/add")
    Boolean add(@RequestBody User user);

    @GetMapping("/getById/{id}")
    User getById(@PathVariable("id") Long id);

}