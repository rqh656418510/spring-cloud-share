package com.clay.shardingjdbc;

import com.clay.shardingjdbc.entity.Order;
import com.clay.shardingjdbc.entity.OrderItem;
import com.clay.shardingjdbc.mapper.OrderItemMapper;
import com.clay.shardingjdbc.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ShardingTest {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    /**
     * 水平分库之多表关联：插入数据测试 <p>
     * 同一个用户的订单表和订单详情表中的数据会插入到同一个数据源中，这样可以避免跨库关联
     */
    @Test
    public void testInsertOrder() {
        for (int i = 1; i <= 3; i++) {
            Order order = new Order();
            order.setOrderNo("000" + i);
            order.setUserId((long) i);
            orderMapper.insert(order);

            for (int j = 1; j <= 2; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderNo("000" + i);
                orderItem.setUserId((long) i);
                orderItem.setPrice(new BigDecimal(10));
                orderItem.setCount(2);
                orderItemMapper.insert(orderItem);
            }
        }
    }

}