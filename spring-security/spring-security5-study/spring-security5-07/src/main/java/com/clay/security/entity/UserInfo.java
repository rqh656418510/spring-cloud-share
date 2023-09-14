package com.clay.security.entity;

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
public class UserInfo implements Serializable {

    private Long id;

    private String username;

    private String password;

}