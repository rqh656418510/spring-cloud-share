package com.clay.dubbo.service;

import com.clay.dubbo.domain.User;

public interface UserService {

    Boolean add(User user);

    User getById(Long id);

}