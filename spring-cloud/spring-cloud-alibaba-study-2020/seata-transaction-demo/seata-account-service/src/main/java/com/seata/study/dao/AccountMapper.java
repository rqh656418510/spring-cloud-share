package com.seata.study.dao;

import com.seata.study.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface AccountMapper {

    public Account findByUser(@Param("userId") Long userId);

    public void update(Account account);

}
