package com.distributed.transaction.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账户信息
 */
public class Account implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
