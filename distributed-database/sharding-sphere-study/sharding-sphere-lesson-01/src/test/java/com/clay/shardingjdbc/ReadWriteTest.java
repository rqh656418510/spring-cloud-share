package com.clay.shardingjdbc;

import com.clay.shardingjdbc.entity.User;
import com.clay.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReadWriteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 写入数据的测试
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUname("张三丰");
        userMapper.insert(user);
    }

}