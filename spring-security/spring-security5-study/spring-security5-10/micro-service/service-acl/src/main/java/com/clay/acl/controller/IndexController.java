package com.clay.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.clay.acl.service.IndexService;
import com.clay.common.base.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 *
 * @author clay
 */
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据Token获取用户信息
     */
    @GetMapping("info")
    public R info() {
        // 获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取菜单
     */
    @GetMapping("menu")
    public R getMenu() {
        // 获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return R.ok().data("permissionList", permissionList);
    }

    /**
     * 退出登录
     */
    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }

}
