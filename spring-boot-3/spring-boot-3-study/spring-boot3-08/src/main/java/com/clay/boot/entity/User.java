package com.clay.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author clay
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private String password;

}
