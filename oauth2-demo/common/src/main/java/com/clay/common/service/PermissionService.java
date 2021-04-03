package com.clay.common.service;

import com.clay.common.entity.Permission;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 */
public interface PermissionService {

    /**
     * 获取用户的权限列表
     *
     * @param userId
     * @return
     */
    List<Permission> getPermissionsByUserId(Long userId);

}
