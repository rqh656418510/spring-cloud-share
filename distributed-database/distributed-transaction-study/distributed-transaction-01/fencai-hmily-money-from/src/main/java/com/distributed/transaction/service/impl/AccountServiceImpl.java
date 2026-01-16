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
    public boolean updateBalance(Long id, BigDecimal delta) {
        // 扣钱操作
        int row = accountMapper.updateBalance(id, delta);

        // 加钱操作
        accountClient.transfer(id, delta.abs());

        return row > 0;
    }

}
