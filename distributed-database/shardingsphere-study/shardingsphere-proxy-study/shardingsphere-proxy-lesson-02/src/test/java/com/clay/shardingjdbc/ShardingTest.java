package com.clay.shardingjdbc;

import com.clay.shardingjdbc.entity.Order;
import com.clay.shardingjdbc.entity.User;
import com.clay.shardingjdbc.mapper.OrderMapper;
import com.clay.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ShardingTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 垂直分库：插入数据测试
     */
    @Test
    public void testInsertOrderAndUser() {
        User user = new User();
        user.setUname("张三");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("00001");
        order.setUserId(user.getId());
        order.setAmount(new BigDecimal(100));
        orderMapper.insert(order);
    }

    /**
     * 垂直分库：查询数据测试
     */
    @Test
    public void testSelectFromOrderAndUser() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
        Order order = orderMapper.selectById(1L);
        System.out.println(order);
    }

}