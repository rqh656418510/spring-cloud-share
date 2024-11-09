package com.clay.service;

import com.clay.entities.Order;
import com.clay.resp.ResultData;

/**
 * @author turing
 * @version 1.0
 */
public interface OrderService {

    /**
     * 创建订单
     */
    ResultData create(Order order);

}
