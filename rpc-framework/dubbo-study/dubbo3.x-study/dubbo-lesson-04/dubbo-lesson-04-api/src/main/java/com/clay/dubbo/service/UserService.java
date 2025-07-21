package com.clay.dubbo.service;

import com.clay.dubbo.domain.User;

public interface UserService {

    Boolean update(User user);

    User getById(Long id);

}