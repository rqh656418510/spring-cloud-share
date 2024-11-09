package com.clay.service;

import com.clay.resp.ResultData;

import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
public interface AccountService {

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  本次消费金额
     */
    ResultData decrease(Long userId, BigDecimal money);

}
