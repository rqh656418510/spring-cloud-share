package com.clay.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_dict") // 真实表名
@Data
public class Dict {

    /**
     * 使用 MyBatis-Plus 的雪花算法来生成 ID <br>
     * 由于广播表的表结构及其表数据（包括主键值）在每个数据库中均完全一致，因此可以使用 MyBatis-Plus 的 ASSIGN_ID（基于雪花算法）主键生成策略
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典类型 <br>
     * 例如: gender / order_status
     */
    private String dictType;

    /**
     * 字典编码 <br>
     * 例如: male / pending
     */
    private String dictCode;

    /**
     * 字典显示值 <br>
     * 例如: 男 / 待支付
     */
    private String dictValue;

}