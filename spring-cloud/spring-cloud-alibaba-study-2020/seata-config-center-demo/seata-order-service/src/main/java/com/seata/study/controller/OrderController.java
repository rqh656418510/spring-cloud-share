package com.seata.study.controller;

import com.seata.study.domain.Order;
import com.seata.study.service.OrderService;
import com.seata.study.vo.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author clay
 * @version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/create")
    public CommonResult createOrder(Order order) {
        return orderService.createOrder(order);
    }

}
