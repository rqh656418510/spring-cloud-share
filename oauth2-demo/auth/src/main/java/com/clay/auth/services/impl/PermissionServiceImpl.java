package com.clay.auth.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clay.auth.mapper.PermissionMapper;
import com.clay.common.entity.Permission;
import com.clay.common.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> getPermissionsByUserId(Long userId) {
        return baseMapper.selectPermissionsByUserId(userId);
    }

}
