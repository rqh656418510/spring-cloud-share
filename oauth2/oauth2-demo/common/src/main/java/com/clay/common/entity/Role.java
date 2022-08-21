package com.clay.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author clay
 * @version 1.0
 */
@TableName("t_role")
public class Role implements Serializable {

    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态  1 有效，0 无效
     */
    private Integer status = 1;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    public Role() {

    }

    public Role(Long id, String name, Integer status, String description, Date createTime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.createTime = createTime;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
