package com.turing.cloud.controller;

import com.turing.cloud.api.PayFeignApi;
import com.turing.cloud.entities.Pay;
import com.turing.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/ordder")
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    /**
     * 新增订单
     *
     * @return
     */
    @PostMapping("/add")
    public ResultData<String> addOrder(@RequestBody Pay pay) {
        log.info("第一步：模拟本地新增订单成功(省略SQL操作)，第二步：再发出支付微服务的远程调用");
        return payFeignApi.addPay(pay);
    }

    /**
     * 获取支付流水
     *
     * @param id 支付流水的ID
     * @return
     */
    @GetMapping("/getPayInfo/{id}")
    public ResultData<Pay> getPayInfo(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    /**
     * 获取微服务应用的信息
     * <p> OpenFeign天然支持负载均衡
     *
     * @return
     */
    @GetMapping("/get/appinfo")
    public ResultData<String> getAppInfo() {
        return payFeignApi.getAppInfo();
    }

}
