package com.clay.shardingsphere;

import com.clay.shardingsphere.mapper.OrderMapper;
import com.clay.shardingsphere.vo.OrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShardingTest {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 水平分库之绑定表：多表关联数据查询测试 <p>
     */
    @Test
    public void testGetOrderAmount() {
        List<OrderVo> orderAmountList = orderMapper.getOrderAmount();
    }

}