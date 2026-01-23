package com.distributed.transaction.controller;

import com.distributed.transaction.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户控制器
 */
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 转账操作
     *
     * @param fromId     扣钱的账户ID
     * @param transMoney 转账金额（正数）
     * @param toId       加钱的账号ID
     */
    @GetMapping("/account/{fromId}/{transMoney}/{toId}")
    public boolean transfer(@PathVariable("fromId") Long fromId, @PathVariable("transMoney") BigDecimal transMoney, @PathVariable("toId") Long toId) {
        // 调用本地的 try 方法（TCC 的三大方法之一）
        return accountService.tryUpdateBalance(fromId, transMoney.negate(), toId);
    }

}