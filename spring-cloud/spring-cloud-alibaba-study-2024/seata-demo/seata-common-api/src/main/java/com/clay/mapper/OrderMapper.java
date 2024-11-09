package com.clay.mapper;

import com.clay.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     *
     * @param order 订单信息
     */
    public int insert(Order order);

    /**
     * 查询订单
     *
     * @param orderId 订单 ID
     * @return
     */
    public Order selectById(@Param("id") Long orderId);

    /**
     * 更新订单状态
     *
     * @param orderId 订单id
     * @param status  订单状态
     */
    public int updateStatus(@Param("id") Long orderId, @Param("status") Integer status);

}