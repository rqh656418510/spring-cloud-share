package com.clay.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.clay.dto.PayDTO;
import com.clay.resp.ResultData;
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
    public ResultData getPayByOrderNumber(@PathVariable("orderNumber") String orderNumber) {
        // 模拟从数据库查询出数据
        PayDTO payDTO = new PayDTO();
        payDTO.setId(1024);
        payDTO.setOrderNo(orderNumber);
        payDTO.setAmount(BigDecimal.valueOf(9.9));
        payDTO.setPayNo("pay:" + UUID.fastUUID());
        payDTO.setUserId(1);

        return ResultData.success(JSONUtil.toJsonStr(payDTO));
    }

}
