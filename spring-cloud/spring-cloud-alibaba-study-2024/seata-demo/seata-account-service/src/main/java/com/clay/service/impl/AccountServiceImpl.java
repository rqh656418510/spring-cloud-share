package com.clay.service.impl;

import com.clay.entities.Account;
import com.clay.mapper.AccountMapper;
import com.clay.resp.ResultData;
import com.clay.resp.ReturnCodeEnum;
import com.clay.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author clay
 * @version 1.0
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public ResultData decrease(Long userId, BigDecimal money) {
        Account account = accountMapper.selectByUserId(userId);
        BigDecimal residue = account.getResidue();
        // 校验参数
        if (money == null || money.compareTo(BigDecimal.ZERO) < 1) {
            return ResultData.fail(ReturnCodeEnum.RC380);
        }
        // 判断余额是否足够
        if (money.compareTo(residue) == 1) {
            return ResultData.fail(ReturnCodeEnum.ACCOUNT_NOT_ENOUGH);
        }

        log.info("------->账户服务中扣减账户余额开始");
        accountMapper.decrease(userId, money);

        // 模拟业务处理抛出异常，全局事务回滚
        // myCompute();

        // 模拟超时异常，全局事务回滚
        myTimeOut();

        log.info("------->账户服务中扣减账户余额结束");

        return ResultData.success();
    }

    /**
     * 模拟超时异常，全局事务回滚
     *
     * <p> OpenFeign 的默认超时时间是60秒
     */
    private static void myTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟业务处理抛出异常，全局事务回滚
     */
    private static void myCompute() {
        int a = 10 / 0;
    }

}
