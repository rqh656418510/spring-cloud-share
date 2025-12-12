package com.clay.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_dict") // 逻辑表名
@Data
public class Dict {

    /**
     * 使用 MyBatis-Plus 的雪花算法来生成 ID <br>
     * 由于广播表的表结构及其表数据在每个数据库中均完全一致，因此可以使用 MyBatis-Plus 的 ASSIGN_ID（基于雪花算法）主键生成策略
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String dictType;

}