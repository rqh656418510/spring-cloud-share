package com.distributed.transaction.service.impl;

import com.distributed.transaction.mapper.AccountMapper;
import com.distributed.transaction.service.AccountService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 添加 @HmilyTCC 注解 <p>
     * 注解 @HmilyTCC 必须指定 confirmMethod 和 cancelMethod 参数
     */
    @Override
    @HmilyTCC(confirmMethod = "confirmUpdateBalance", cancelMethod = "cancelUpdateBalance")
    public boolean tryUpdateBalance(Long id, BigDecimal delta) {
        System.out.println("入账 开始");
        return Boolean.TRUE;
    }

    @Override
    public boolean confirmUpdateBalance(Long id, BigDecimal delta) {
        System.out.println("入账 确认");
        accountMapper.updateBalance(id, delta);
        return Boolean.TRUE;
    }

    @Override
    public boolean cancelUpdateBalance(Long id, BigDecimal delta) {
        System.out.println("入账 取消");
        return Boolean.TRUE;
    }

}