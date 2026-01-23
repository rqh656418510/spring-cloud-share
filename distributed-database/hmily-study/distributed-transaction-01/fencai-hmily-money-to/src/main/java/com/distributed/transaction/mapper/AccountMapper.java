package com.distributed.transaction.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountMapper {

    /**
     * 按增量更新账户余额，并发环境下可避免「丢失更新」问题
     *
     * @param id    账户ID
     * @param delta 余额变动值（正数：增加余额；负数：减少余额）
     * @return 影响行数（1：成功；0：账户不存在或更新条件不满足）
     */
    int updateBalance(@Param("id") Long id, @Param("delta") BigDecimal delta);

}