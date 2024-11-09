package com.clay.mapper;

import com.clay.entities.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface AccountMapper {

    /**
     * 获取账户信息
     *
     * @param userId 用户id
     * @return
     */
    Account selectByUserId(@Param("userId") Long userId);

    /**
     * 扣减余额
     *
     * @param userId 用户id
     * @param money  本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);

}