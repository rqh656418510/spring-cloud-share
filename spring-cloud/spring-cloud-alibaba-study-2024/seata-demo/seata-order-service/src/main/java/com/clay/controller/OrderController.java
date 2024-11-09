package com.clay.controller;

import com.clay.entities.Order;
import com.clay.resp.ResultData;
import com.clay.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/order/create")
    public ResultData create(Order order) {
        return orderService.create(order);
    }

}