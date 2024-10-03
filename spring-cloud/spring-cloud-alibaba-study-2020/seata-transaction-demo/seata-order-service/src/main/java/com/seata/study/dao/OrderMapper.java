package com.seata.study.dao;

import com.seata.study.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface OrderMapper {

    public void create(Order order);

    public void update(@Param("id") Long orderId, @Param("status") Integer status);
}
