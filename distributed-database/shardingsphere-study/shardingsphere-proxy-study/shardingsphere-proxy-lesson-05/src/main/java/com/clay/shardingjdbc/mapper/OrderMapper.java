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
     * 查询每个订单的订单号和总订单金额 <p>
     * 由于 ShardingSphere-Proxy 中配置了绑定表，因此这里执行多表关联查询时，必须使用分片键（"order_no"）进行关联，否则会出现笛卡尔积关联或跨库关联，从而严重影响查询效率
     */
    @Select({"SELECT o.order_no, SUM(i.price * i.count) AS amount",
        "FROM t_order o JOIN t_order_item i ON o.order_no = i.order_no",
        "GROUP BY o.order_no"})
    List<OrderVo> getOrderAmount();

}