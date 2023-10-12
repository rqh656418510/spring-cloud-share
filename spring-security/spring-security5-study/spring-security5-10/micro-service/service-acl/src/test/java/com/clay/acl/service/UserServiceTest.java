package com.clay.acl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clay.acl.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void list() {
        List<User> list = userService.list(new QueryWrapper<>());
        list.forEach(user -> {
            log.info("{}", user.getUsername());
        });
    }

}
