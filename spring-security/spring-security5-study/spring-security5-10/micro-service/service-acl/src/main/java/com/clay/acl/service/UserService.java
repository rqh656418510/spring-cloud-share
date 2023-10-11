package com.clay.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clay.acl.entity.User;

/**
 * 用户服务类
 *
 * @author clay
 */
public interface UserService extends IService<User> {

    // 获取用户信息
    User selectByUsername(String username);

}
