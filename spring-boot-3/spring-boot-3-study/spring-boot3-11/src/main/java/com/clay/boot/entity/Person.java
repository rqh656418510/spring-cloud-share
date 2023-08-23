package com.clay.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private Long id;
    private String userName;
    private String email;
    private Integer age;
    private String role;

}