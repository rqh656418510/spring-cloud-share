package com.clay.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author clay
 */
@Data
@TableName(value = "role_menu")
public class RoleMenu implements Serializable {

    private Long rid;

    private Long mid;

}