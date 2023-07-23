package com.clay.boot.web.biz;

import com.clay.boot.web.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clay
 */
@Slf4j
@Component
public class UserBizHandler {

    public ServerResponse getUser(ServerRequest serverRequest) throws Exception {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        User user = new User(id, "Peter1", "peter@gmail.com", 18, "pm");
        return ServerResponse.ok().body(user);
    }

    public ServerResponse listUser(ServerRequest serverRequest) throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Peter1", "peter@gmail.com", 18, "pm"));
        users.add(new User(2L, "Peter2", "peter@gmail.com", 16, "admin"));
        users.add(new User(3L, "Peter3", "peter@gmail.com", 18, "pm"));
        return ServerResponse.ok().body(users);
    }

    public ServerResponse addUser(ServerRequest serverRequest) throws Exception {
        User user = serverRequest.body(User.class);
        log.info("user save success, {}", user.toString());
        return ServerResponse.ok().build();
    }

    public ServerResponse deleteUser(ServerRequest serverRequest) throws Exception {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        log.info("user {} delete success", id);
        return ServerResponse.ok().build();
    }

    public ServerResponse updateUser(ServerRequest serverRequest) throws Exception {
        User user = serverRequest.body(User.class);
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        log.info("user {} update success, {}", id, user.toString());
        return ServerResponse.ok().build();
    }

}
