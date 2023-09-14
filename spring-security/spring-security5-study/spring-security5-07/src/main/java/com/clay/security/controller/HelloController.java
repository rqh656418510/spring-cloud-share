package com.clay.security.controller;

import com.clay.security.entity.UserInfo;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clay
 */
@RestController
public class HelloController {

    @PreAuthorize("hasRole('ROLE_sale')")
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Security";
    }

    @PreAuthorize("hasAnyRole('ROLE_sale,ROLE_admin')")
    @RequestMapping("/hello2")
    public String hello2() {
        return "Hello Spring Security";
    }

    @PostAuthorize("hasAuthority('manager')")
    @RequestMapping("/goodbye")
    public String goodbye() {
        return "Goodbye Spring Security";
    }

    @PostAuthorize("hasAnyAuthority('manager,hr')")
    @RequestMapping("/goodbye2")
    public String goodbye2() {
        return "Goodbye Spring Security";
    }

    @PreAuthorize("hasRole('ROLE_sale')")
    @PostFilter("filterObject.username == 'wangwu'")
    @RequestMapping("/goodnight")
    public List<UserInfo> goodnight() {
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo(1L, "wangwu", "123456"));
        list.add(new UserInfo(2L, "zhangsan", "123456"));
        return list;
    }

    @PreAuthorize("hasRole('ROLE_sale')")
    @PreFilter(value = "filterObject.id % 2 == 0")
    @RequestMapping("/goodnight2")
    public List<UserInfo> goodnight2(@RequestBody List<UserInfo> list) {
        list.forEach(t -> {
            System.out.println(t.getId() + " " + t.getUsername());
        });
        return list;
    }

}
