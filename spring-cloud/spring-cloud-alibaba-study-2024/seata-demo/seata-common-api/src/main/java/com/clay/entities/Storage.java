package com.clay.entities;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author clay
 * @version 1.0
 */
@ToString
public class Storage implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;

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
     * 获取总库存
     *
     * @return total - 总库存
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置总库存
     *
     * @param total 总库存
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取已用库存
     *
     * @return used - 已用库存
     */
    public Integer getUsed() {
        return used;
    }

    /**
     * 设置已用库存
     *
     * @param used 已用库存
     */
    public void setUsed(Integer used) {
        this.used = used;
    }

    /**
     * 获取剩余库存
     *
     * @return residue - 剩余库存
     */
    public Integer getResidue() {
        return residue;
    }

    /**
     * 设置剩余库存
     *
     * @param residue 剩余库存
     */
    public void setResidue(Integer residue) {
        this.residue = residue;
    }
}