package com.clay.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author clay
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;
    private String name;
    private Integer age;

}