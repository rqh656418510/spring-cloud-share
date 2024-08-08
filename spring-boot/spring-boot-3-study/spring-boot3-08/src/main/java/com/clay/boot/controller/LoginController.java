package com.clay.boot.controller;

import com.clay.boot.entity.User;
import com.clay.boot.event.EventPublisher;
import com.clay.boot.event.LoginSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author clay
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private EventPublisher eventPublisher;

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        // 处理用户登录业务

        // 发送用户登录成功的事件
        LoginSuccessEvent event = new LoginSuccessEvent(user);
        eventPublisher.sendEvent(event);

        return "Login Success";
    }

}
