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
    public boolean updateBalance(Long id, BigDecimal delta) {
        int row = accountMapper.updateBalance(id, delta);
        return row > 0;
    }

}
