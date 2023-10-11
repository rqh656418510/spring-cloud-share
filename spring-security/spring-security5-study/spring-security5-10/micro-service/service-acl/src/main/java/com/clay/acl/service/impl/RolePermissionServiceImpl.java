package com.clay.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clay.acl.entity.RolePermission;
import com.clay.acl.mapper.RolePermissionMapper;
import com.clay.acl.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限服务实现类
 *
 * @author clay
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
