package com.clay.common.service;

import com.clay.common.entity.User;

/**
 * @author clay
 * @version 1.0
 */
public interface UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

}
