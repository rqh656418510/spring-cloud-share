package com.springcloud.study.domain;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private int age;

    private String userName;

    public User() {

    }

    public User(Long id, int age, String userName) {
        this.id = id;
        this.age = age;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
