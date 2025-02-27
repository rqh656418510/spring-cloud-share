package com.clay.dubbo.service;

import com.clay.dubbo.domain.User;

public interface UserService {

    String sayHello(String name);

    User getById(Long id);

}
