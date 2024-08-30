package com.clay.cache.service;

import com.clay.cache.entity.User;

public interface UserService {

    void add(User user);

    void delete(Long id);

    void update(User user);

    User get(Long id);

}
