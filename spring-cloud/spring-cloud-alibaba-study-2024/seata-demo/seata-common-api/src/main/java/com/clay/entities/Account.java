package com.clay.entities;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@ToString
public class Account implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 已用余额
     */
    private BigDecimal used;

    /**
     * 剩余可用额度
     */
    private BigDecimal residue;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取总额度
     *
     * @return total - 总额度
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置总额度
     *
     * @param total 总额度
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取已用余额
     *
     * @return used - 已用余额
     */
    public BigDecimal getUsed() {
        return used;
    }

    /**
     * 设置已用余额
     *
     * @param used 已用余额
     */
    public void setUsed(BigDecimal used) {
        this.used = used;
    }

    /**
     * 获取剩余可用额度
     *
     * @return residue - 剩余可用额度
     */
    public BigDecimal getResidue() {
        return residue;
    }

    /**
     * 设置剩余可用额度
     *
     * @param residue 剩余可用额度
     */
    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }
}

