package com.clay.security.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clay.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author clay
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void list() {
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        users.forEach(user -> {
            log.info(user.getUsername());
        });
    }

}
