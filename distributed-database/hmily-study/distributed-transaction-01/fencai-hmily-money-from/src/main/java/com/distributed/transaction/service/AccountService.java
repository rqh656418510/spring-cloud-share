package com.distributed.transaction.service;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 尝试更新账户余额
     *
     * @param fromId 扣钱的账户ID
     * @param delta  余额变动值（正数：增加余额；负数：减少余额）
     * @param toId   加钱的账号ID
     */
    boolean tryUpdateBalance(Long fromId, BigDecimal delta, Long toId);

    /**
     * 确认更新账户余额
     *
     * @param fromId 扣钱的账户ID
     * @param delta  余额变动值（正数：增加余额；负数：减少余额）
     * @param toId   加钱的账号ID
     */
    boolean confirmUpdateBalance(Long fromId, BigDecimal delta, Long toId);

    /**
     * 取消更新账户余额
     *
     * @param fromId 扣钱的账户ID
     * @param delta  余额变动值（正数：增加余额；负数：减少余额）
     * @param toId   加钱的账号ID
     */
    boolean cancelUpdateBalance(Long fromId, BigDecimal delta, Long toId);

}