package com.clay.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author clay
 */
@Data
@TableName(value = "user_role")
public class UserRole implements Serializable {

    private Long uid;

    private Long rid;

}