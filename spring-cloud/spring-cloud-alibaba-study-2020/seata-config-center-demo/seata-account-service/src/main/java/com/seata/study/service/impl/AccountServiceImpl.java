package com.seata.study.service.impl;

import com.seata.study.dao.AccountMapper;
import com.seata.study.domain.Account;
import com.seata.study.enums.SystemCode;
import com.seata.study.service.AccountService;
import com.seata.study.vo.CommonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public CommonResult decrease(Long userId, BigDecimal money) {
        Account account = accountMapper.findByUser(userId);
        BigDecimal total = account.getTotal();
        BigDecimal used = account.getUsed();
        BigDecimal residue = account.getResidue();
        // 模拟业务处理超时
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 校验参数
        if (money == null || money.compareTo(BigDecimal.ZERO) < 1) {
            return new CommonResult(SystemCode.ERROR_PARAMETER);
        }
        // 判断余额是否足够
        if (money.compareTo(residue) == 1) {
            return new CommonResult(SystemCode.ACCOUNT_NOT_ENOUGH);
        }
        //
        // 扣减余额
        account.setUsed(account.getUsed().add(money));
        account.setResidue(account.getResidue().subtract(money));
        accountMapper.update(account);
        return new CommonResult();
    }
}
