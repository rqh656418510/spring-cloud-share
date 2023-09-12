package com.clay.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author clay
 */
@Data
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;

}
