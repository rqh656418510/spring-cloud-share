package com.clay.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.clay.dto.PayDTO;
import com.clay.resp.ResultData;
import com.clay.resp.ReturnCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @GetMapping("/pay/get/{orderNumber}")
    @SentinelResource(value = "getPayByOrderNumber", blockHandler = "handlerBlockHandler")
    public ResultData getPayByOrderNumber(@PathVariable("orderNumber") String orderNumber) {
        // 模拟从数据库查询出数据
        PayDTO payDTO = new PayDTO();
        payDTO.setId(1024);
        payDTO.setOrderNo(orderNumber);
        payDTO.setAmount(BigDecimal.valueOf(9.9));
        payDTO.setPayNo("pay:" + UUID.fastUUID());
        payDTO.setUserId(1);

        return ResultData.success(JSON.toJSONString(payDTO));
    }

    public ResultData handlerBlockHandler(String orderNumber, BlockException exception) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "服务暂时不可用，触发 Sentinel 的控制规则");
    }

}
