package com.clay.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clay.shardingjdbc.entity.Order;
import com.clay.shardingjdbc.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ShardingTest {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 水平分库：插入数据测试
     */
    @Test
    public void testInsertOrder() {
        for (int i = 1; i <= 5; i++) {
            Order order = new Order();
            order.setOrderNo("000" + i);
            order.setUserId((long) i);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }

    /**
     * 水平分库：查询所有数据 <br>
     * 查询了两个数据源，每个数据源中使用 UNION ALL 连接两个表
     */
    @Test
    public void testShardingSelectAll() {
        List<Order> orders = orderMapper.selectList(null);
    }

    /**
     * 水平分库：根据 user_id（分片键）查询记录 <br>
     * 查询了一个数据源，每个数据源中使用 UNION ALL 连接两个表
     */
    @Test
    public void testShardingSelectByUserId() {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id", 1L);
        List<Order> orders = orderMapper.selectList(orderQueryWrapper);
    }

}