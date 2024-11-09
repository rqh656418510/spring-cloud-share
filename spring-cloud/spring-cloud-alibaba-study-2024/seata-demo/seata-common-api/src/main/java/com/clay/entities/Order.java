package com.clay.entities;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@ToString
public class Order implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 订单状态: 0:创建中; 1:已完结
     */
    private Integer status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return userId - 用户id
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
     * 获取产品id
     *
     * @return productId - 产品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取数量
     *
     * @return count - 数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置数量
     *
     * @param count 数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取订单状态: 0:创建中; 1:已完结
     *
     * @return status - 订单状态: 0:创建中; 1:已完结
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态: 0:创建中; 1:已完结
     *
     * @param status 订单状态: 0:创建中; 1:已完结
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}