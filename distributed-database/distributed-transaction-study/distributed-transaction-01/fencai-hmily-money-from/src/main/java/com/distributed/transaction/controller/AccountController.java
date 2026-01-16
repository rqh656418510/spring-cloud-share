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
     * @param id         账户ID
     * @param transMoney 转账金额（正数：增加余额；负数：减少余额）
     * @return true 成功，false 失败
     */
    @GetMapping("/account/{id}/{transMoney}")
    public boolean transfer(@PathVariable("id") Long id, @PathVariable("transMoney") BigDecimal transMoney) {
        return accountService.updateBalance(id, transMoney);
    }

}