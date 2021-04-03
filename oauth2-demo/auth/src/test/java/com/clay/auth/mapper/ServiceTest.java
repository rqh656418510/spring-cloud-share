package com.clay.auth.mapper;

import com.alibaba.fastjson.JSONObject;
import com.clay.common.entity.Permission;
import com.clay.common.entity.User;
import com.clay.common.service.PermissionService;
import com.clay.common.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Test
    public void getUserByUsername() {
        User user = userService.getUserByUsername("wangwu");
        Assert.notNull(user, "user is null");
    }

    @Test
    public void selectPermissionsByUserId() {
        List<Permission> list = permissionService.getPermissionsByUserId(1L);
        Assert.notEmpty(list, "permission list is empty");
        System.out.println(JSONObject.toJSONString(list));
    }

}
