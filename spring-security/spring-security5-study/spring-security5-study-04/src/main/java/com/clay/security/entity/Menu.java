package com.clay.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author clay
 */
@Data
@TableName(value = "menu")
public class Menu implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String url;

    private Long parentId;

    private String permission;

}