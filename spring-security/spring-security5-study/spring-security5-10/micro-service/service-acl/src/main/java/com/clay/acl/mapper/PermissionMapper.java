package com.clay.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clay.acl.entity.Permission;

import java.util.List;

/**
 * 权限 Mapper 接口
 *
 * @author clay
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);

}
