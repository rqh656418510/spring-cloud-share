package com.clay.cloud.controller;

import com.clay.cloud.dto.PayDTO;
import com.clay.cloud.entities.Pay;
import com.clay.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/pay")
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增支付流水方法，JSON串作参数")
    public String addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        return "Success to add, result value is " + i;
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PostMapping("/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return "Success to update, result value is " + i;
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按照 ID 查流水", description = "查询支付流水方法")
    public Pay getById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping("/pay/getAll")
    @Operation(summary = "获取所有支付流水", description = "查询所有支付流水方法")
    public List<Pay> getAll() {
        return payService.getAll();
    }

}
