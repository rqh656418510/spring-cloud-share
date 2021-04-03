package com.clay.shop.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author clay
 * @version 1.0
 */
@Api("订单接口")
@RestController
@RequestMapping("/order")
public class OrderController {

    @ApiOperation(value = "获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "Long")
    })
    @GetMapping("/get/{orderId}")
    public Object getById(@PathVariable("orderId") Long orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", 2L);
        jsonObject.put("userId", 1L);
        jsonObject.put("id", orderId);
        jsonObject.put("money", 20.3);
        jsonObject.put("createTime", new Date());
        return jsonObject;
    }

}
