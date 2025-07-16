package com.clay.dubbo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

}
