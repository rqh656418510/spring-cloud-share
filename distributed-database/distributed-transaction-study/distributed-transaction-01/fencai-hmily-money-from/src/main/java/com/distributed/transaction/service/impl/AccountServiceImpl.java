package com.distributed.transaction.service.impl;

import com.distributed.transaction.client.AccountClient;
import com.distributed.transaction.mapper.AccountMapper;
import com.distributed.transaction.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountClient accountClient;

    @Override
    public void tryUpdateBalance(Long fromId, BigDecimal delta, Long toId) {
        System.out.println("出账 开始");
        // 扣钱
        accountMapper.updateBalance(fromId, delta);

        // 通过 Feign 远程调用加钱服务的 try 方法（TCC 的三大方法之一）
        accountClient.transfer(toId, delta.negate());
    }

    @Override
    public void confirmUpdateBalance(Long fromId, BigDecimal delta, Long toId) {
        System.out.println("出账 确认");
    }

    @Override
    public void cancelUpdateBalance(Long fromId, BigDecimal delta, Long toId) {
        System.out.println("出账 取消");
        // 还钱（任意一个 try 方法执行失败时）
        accountMapper.updateBalance(fromId, delta.negate());
    }

}