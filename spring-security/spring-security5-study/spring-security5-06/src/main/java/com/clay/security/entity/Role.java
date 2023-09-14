package com.clay.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author clay
 */
@TableName(value = "role")
@Data
public class Role implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

}