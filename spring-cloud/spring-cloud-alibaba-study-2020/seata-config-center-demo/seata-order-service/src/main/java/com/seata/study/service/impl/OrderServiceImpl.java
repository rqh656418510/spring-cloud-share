package com.seata.study.service.impl;

import com.seata.study.client.AccountClient;
import com.seata.study.client.StorageClient;
import com.seata.study.dao.OrderMapper;
import com.seata.study.domain.Order;
import com.seata.study.enums.OrderStatus;
import com.seata.study.service.OrderService;
import com.seata.study.vo.CommonResult;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author clay
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AccountClient accountClient;

    @Resource
    private StorageClient storageClient;

    @Override
    @GlobalTransactional(name = "create-order", rollbackFor = Exception.class)
    public CommonResult createOrder(Order order) {
        // 创建订单
        orderMapper.create(order);
        // 扣减商品库存
        storageClient.decrease(order.getProductId(), order.getCount());
        // 扣减账户余额
        accountClient.decrease(order.getUserId(), order.getMoney());
        //更新订单状态
        orderMapper.update(order.getId(), OrderStatus.FINISHED.getValue());
        return new CommonResult();
    }
}
