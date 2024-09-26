package com.turing.cloud.controller;

import cn.hutool.core.lang.UUID;
import com.turing.cloud.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author turing
 * @version 1.0
 */
@RestController
@RequestMapping("/pay")
public class PayCircuitController {

    /**
     * Resilience4j CircuitBreaker 的使用例子
     */
    @GetMapping(value = "/circuit/{id}")
    public ResultData<String> circuit(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("Circuit id 不能负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ResultData.success("Hello, circuit! inputId :  " + id + " \t " + UUID.fastUUID());
    }

}
