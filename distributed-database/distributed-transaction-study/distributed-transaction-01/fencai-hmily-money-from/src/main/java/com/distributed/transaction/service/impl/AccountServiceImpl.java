package com.distributed.transaction.service.impl;

import com.distributed.transaction.client.AccountClient;
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

    @Resource
    private AccountClient accountClient;

    /**
     * 添加 @HmilyTCC 注解 <p>
     * 注解 @HmilyTCC 必须指定 confirmMethod 和 cancelMethod 参数
     */
    @Override
    @HmilyTCC(confirmMethod = "confirmUpdateBalance", cancelMethod = "cancelUpdateBalance")
    public boolean tryUpdateBalance(Long fromId, BigDecimal delta, Long toId) {
        System.out.println("出账 开始");
        // 扣钱
        accountMapper.updateBalance(fromId, delta);

        // 通过 Feign 远程调用加钱服务的 try 方法（TCC 的三大方法之一）
        accountClient.transfer(toId, delta.negate());

        // 故意抛出运行时异常，验证 Cancel 方法是否会被执行（回滚操作）
        // int num = 10 / 0;

        return Boolean.TRUE;
    }

    @Override
    public boolean confirmUpdateBalance(Long fromId, BigDecimal delta, Long toId) {
        System.out.println("出账 确认");
        return Boolean.TRUE;
    }

    @Override
    public boolean cancelUpdateBalance(Long fromId, BigDecimal delta, Long toId) {
        System.out.println("出账 取消");
        // 还钱（任意一个 try 方法执行失败时）
        accountMapper.updateBalance(fromId, delta.negate());
        return Boolean.TRUE;
    }

}