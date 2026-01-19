package com.distributed.transaction.service;

import org.dromara.hmily.annotation.Hmily;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 尝试更新账户余额
     *
     * @param id    账户ID
     * @param delta 余额变动值（正数：增加余额；负数：减少余额）
     */
    @Hmily(confirmMethod = "confirmUpdateBalance", cancelMethod = "cancelUpdateBalance")
    void tryUpdateBalance(Long id, BigDecimal delta);

    /**
     * 确认更新账户余额
     *
     * @param id    账户ID
     * @param delta 余额变动值（正数：增加余额；负数：减少余额）
     */
    void confirmUpdateBalance(Long id, BigDecimal delta);

    /**
     * 取消更新账户余额
     *
     * @param id    账户ID
     * @param delta 余额变动值（正数：增加余额；负数：减少余额）
     */
    void cancelUpdateBalance(Long id, BigDecimal delta);

}