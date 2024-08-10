package com.turing.cloud.controller;

import com.turing.cloud.dto.PayDTO;
import com.turing.cloud.entities.Pay;
import com.turing.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
 * @author turing
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
    public String addPay(
        @Parameter(in = ParameterIn.DEFAULT, description = "支付流水，JSON串作参数", schema = @Schema(implementation = Pay.class))
        @RequestBody Pay pay) {
        int i = payService.add(pay);
        return "Success to add, result value is " + i;
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public Integer deletePay(
        @Parameter(in = ParameterIn.PATH, description = "ID", required = true) @PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PostMapping("/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public String updatePay(
        @Parameter(in = ParameterIn.DEFAULT, description = "支付流水，JSON串作参数", schema = @Schema(implementation = PayDTO.class))
        @RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return "Success to update, result value is " + i;
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按照 ID 查流水", description = "查询支付流水方法")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class)))})
    public Pay getById(
        @Parameter(in = ParameterIn.PATH, description = "ID", required = true) @PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping("/pay/getAll")
    @Operation(summary = "获取所有支付流水", description = "查询所有支付流水方法")
    public List<Pay> getAll() {
        return payService.getAll();
    }

}
