package com.turing.cloud.controller;

import cn.hutool.core.lang.UUID;
import com.turing.cloud.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@RestController
@RequestMapping("/pay")
public class PayMicrometerController {

    /**
     * 该接口用于测试 Micrometer 链路监控
     */
    @GetMapping("/micrometer/{id}")
    public ResultData<String> micrometer(@PathVariable("id") Integer id) {
        return ResultData.success("Hello, welcome to Micrometer, inputId : " + id + "\t    " + UUID.fastUUID());
    }

}
