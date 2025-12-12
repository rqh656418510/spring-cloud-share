package com.clay.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_dict") // 逻辑表名
@Data
public class Dict {

    /**
     * 使用 MyBatis-Plus 的雪花算法来生成 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String dictType;

}