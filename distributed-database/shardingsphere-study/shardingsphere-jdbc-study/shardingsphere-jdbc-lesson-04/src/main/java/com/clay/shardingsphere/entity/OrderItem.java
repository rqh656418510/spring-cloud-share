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
     * 当项目中配置了 ShardingSphere-JDBC 的分布式序列策略时，会自动使用 ShardingSphere-JDBC 的分布式序列策略
     * 当项目中没有配置 ShardingSphere-JDBC 的分布式序列策略时，自动依赖数据库的主键自增策略
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderNo;

    private Long userId;

    private BigDecimal price;

    private Integer count;

}