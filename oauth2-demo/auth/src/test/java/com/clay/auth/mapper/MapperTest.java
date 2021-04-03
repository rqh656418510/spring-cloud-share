package com.clay.auth.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clay.common.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Test
    public void userList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        List<User> list = userMapper.selectList(wrapper);
        Assert.notEmpty(list, "user list is empty");
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void roleList() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        List<Role> list = roleMapper.selectList(wrapper);
        Assert.notEmpty(list, "role list is empty");
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void permissionList() {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        List<Permission> list = permissionMapper.selectList(wrapper);
        Assert.notEmpty(list, "permission list is empty");
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void userRoleList() {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        List<UserRole> list = userRoleMapper.selectList(wrapper);
        Assert.notEmpty(list, "user role list is empty");
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void rolePermissionList() {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        List<RolePermission> list = rolePermissionMapper.selectList(wrapper);
        Assert.notEmpty(list, "role permission list is empty");
        System.out.println(JSONObject.toJSONString(list));
    }

}
