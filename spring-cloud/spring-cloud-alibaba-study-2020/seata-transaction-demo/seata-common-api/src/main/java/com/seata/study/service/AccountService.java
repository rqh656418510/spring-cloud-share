package com.seata.study.service;

import com.seata.study.vo.CommonResult;

import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
public interface AccountService {

    public CommonResult decrease(Long userId, BigDecimal money);

}
