package com.seata.study.controller;

import com.seata.study.service.AccountService;
import com.seata.study.vo.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/decrease")
    public CommonResult decrease(Long userId, BigDecimal money) {
        return accountService.decrease(userId, money);
    }
}
