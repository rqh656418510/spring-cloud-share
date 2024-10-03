package com.seata.study.service;

import com.seata.study.domain.Order;
import com.seata.study.vo.CommonResult;

/**
 * @author clay
 * @version 1.0
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     */
    public CommonResult createOrder(Order order);

}
