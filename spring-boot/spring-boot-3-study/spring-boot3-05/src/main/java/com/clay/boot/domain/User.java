package com.clay.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author clay
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名称
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String passwd;

}