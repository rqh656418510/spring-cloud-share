package com.clay.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 *
 * @author clay
 * @version 1.0
 */
@TableName("t_permission")
public class Permission implements Serializable {

    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 权限标识符
     */
    private String code;

    /**
     * URL
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    public Permission() {

    }

    public Permission(Long id, String code, String url, String description, Date createTime) {
        this.id = id;
        this.code = code;
        this.url = url;
        this.description = description;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
