package com.clay.shardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clay.shardingjdbc.entity.Order;
import com.clay.shardingjdbc.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 多表关联查询 <p>
     * 查询每个订单的订单号和总订单金额
     */
    @Select({"SELECT o.order_no, SUM(i.price * i.count) AS amount",
        "FROM t_order o JOIN t_order_item i ON o.order_no = i.order_no",
        "GROUP BY o.order_no"})
    List<OrderVo> getOrderAmount();

}