package com.distributed.transaction.service;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 按增量更新账户余额
     *
     * @param id    账户ID
     * @param delta 余额变动值（正数：增加；负数：减少）
     * @return true 成功，false 失败
     */
    boolean updateBalance(Long id, BigDecimal delta);

}
