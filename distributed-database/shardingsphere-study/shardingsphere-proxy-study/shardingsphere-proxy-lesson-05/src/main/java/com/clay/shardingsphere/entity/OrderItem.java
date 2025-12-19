package com.clay.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("t_order_item") // 逻辑表名
@Data
public class OrderItem {

    /**
     * MyBatis-Plus 的 ID 策略为 None <br>
     * 当 ShardingSphere-Proxy 配置了分布式序列策略，会自动注入 ID
     */
    @TableId(type = IdType.NONE)
    private Long id;

    private String orderNo;

    private Long userId;

    private BigDecimal price;

    private Integer count;

}