package com.clay.limit.controller;

import cn.hutool.core.util.IdUtil;
import com.clay.limit.annotations.RedisLimitAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/limit")
    @RedisLimitAnnotation(key = "orderLimit", limit = 5, expire = 10, msg = "当前排队人数较多，请稍后再试！")
    public String limit() {
        return "正常业务返回，订单流水：" + IdUtil.fastUUID();
    }

}
