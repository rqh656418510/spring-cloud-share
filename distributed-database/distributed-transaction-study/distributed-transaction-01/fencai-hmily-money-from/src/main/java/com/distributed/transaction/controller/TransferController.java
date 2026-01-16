package com.distributed.transaction.controller;

import com.distributed.transaction.mapper.AccountMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 转账控制器
 */
@RestController
public class TransferController {

    @Resource
    private AccountMapper mapper;

    @GetMapping("/transfer")
    public int transfer() {
        return mapper.updateBalance(1L, new BigDecimal((5)));
    }

}
