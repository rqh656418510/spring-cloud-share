package com.clay.shardingjdbc;

import com.clay.shardingjdbc.entity.Order;
import com.clay.shardingjdbc.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ShardingTest {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 水平分库：插入数据测试
     */
    @Test
    public void testInsertOrder() {
        for (int i = 1; i <= 2; i++) {
            Order order = new Order();
            order.setOrderNo("0001");
            order.setUserId((long) i);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }

}