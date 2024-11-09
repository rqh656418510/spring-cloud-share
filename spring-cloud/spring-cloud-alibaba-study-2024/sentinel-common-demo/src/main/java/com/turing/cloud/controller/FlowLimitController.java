package com.turing.cloud.controller;

import com.turing.cloud.resp.ResultData;
import com.turing.cloud.service.FlowLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author turing
 * @version 1.0
 */
@RestController
public class FlowLimitController {

    @Autowired
    private FlowLimitService flowLimitService;

    /**
     * 演示直接流控模式
     */
    @GetMapping("/testA")
    public ResultData<String> testA() {
        return ResultData.success("----> testA");
    }

    /**
     * 演示关联流控模式
     */
    @GetMapping("/testB")
    public ResultData<String> testB() {
        return ResultData.success("----> testB");
    }

    /**
     * 演示链路流控模式
     */
    @GetMapping("/testC")
    public ResultData<String> testC() {
        flowLimitService.common();
        return ResultData.success("------testC");
    }

    /**
     * 演示链路流控模式
     */
    @GetMapping("/testD")
    public ResultData<String> testD() {
        flowLimitService.common();
        return ResultData.success("------testD");
    }

    /**
     * 演示排队等待流控效果
     */
    @GetMapping("/testE")
    public ResultData<String> testE() {
        System.out.println(System.currentTimeMillis() + " testE 排队等待");
        return ResultData.success("------testE");
    }

    /**
     * 演示熔断规则-慢调用比例
     */
    @GetMapping("/testF")
    public ResultData<String> testF() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultData.success("------testF 新增熔断规则-慢调用比例");
    }

    /**
     * 演示熔断规则-异常比例
     */
    @GetMapping("/testG")
    public String testG() {
        int age = 10 / 0;
        return "------testG 新增熔断规则-异常比例";
    }

    /**
     * 演示熔断规则-异常数
     */
    @GetMapping("/testH")
    public String testH() {
        int age = 10 / 0;
        return "------testH 新增熔断规则-异常数 ";
    }

}
