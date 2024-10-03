package com.seata.study;

import com.seata.study.dao.OrderMapper;
import com.seata.study.domain.Order;
import com.seata.study.enums.OrderStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    @Resource
    private OrderMapper orderDao;

    @Test
    public void create() {
        Order order = new Order(null, 1L, 1L, 50L, new BigDecimal(100), OrderStatus.CREATING.getValue());
        orderDao.create(order);
    }

    @Test
    public void update() {
        orderDao.update(1L, 1);
    }

}
