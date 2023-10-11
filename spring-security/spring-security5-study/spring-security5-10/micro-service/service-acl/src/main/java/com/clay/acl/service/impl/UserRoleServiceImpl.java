package com.clay.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clay.acl.entity.UserRole;
import com.clay.acl.mapper.UserRoleMapper;
import com.clay.acl.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色服务实现类
 *
 * @author clay
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
