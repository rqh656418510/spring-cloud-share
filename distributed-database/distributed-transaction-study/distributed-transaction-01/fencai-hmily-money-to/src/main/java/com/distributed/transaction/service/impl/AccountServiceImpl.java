package com.distributed.transaction.service.impl;

import com.distributed.transaction.mapper.AccountMapper;
import com.distributed.transaction.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public void tryUpdateBalance(Long id, BigDecimal delta) {
        System.out.println("入账 开始");
    }

    @Override
    public void confirmUpdateBalance(Long id, BigDecimal delta) {
        System.out.println("入账 确认");
        accountMapper.updateBalance(id, delta);
    }

    @Override
    public void cancelUpdateBalance(Long id, BigDecimal delta) {
        System.out.println("入账 取消");
    }

}