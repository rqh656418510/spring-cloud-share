package com.clay.shardingjdbc;

import com.clay.shardingjdbc.entity.User;
import com.clay.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ReadWriteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 读写分离测试
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUname("张三丰");
        userMapper.insert(user);

        List<User> list = userMapper.selectList(null);
    }

    /**
     * 事务一致性测试 <p>
     * 当使用 @Transactional 事务注解后，所有 SQL 语句（包括读和写）都是在主库（master）执行
     */
    @Test
    @Transactional
    public void testTransaction() {
        User user = new User();
        user.setUname("李思思");
        userMapper.insert(user);

        List<User> list = userMapper.selectList(null);
    }

    /**
     * 负载均衡测试
     */
    @Test
    public void testLoadBalance() {
        List<User> users1 = userMapper.selectList(null); // 执行第一次查询，路由到 slave1 库
        List<User> users2 = userMapper.selectList(null); // 执行第二次查询，路由到 slave2 库
    }

}