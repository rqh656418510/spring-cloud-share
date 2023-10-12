package com.clay.acl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clay.acl.entity.User;
import com.clay.acl.service.RoleService;
import com.clay.acl.service.UserService;
import com.clay.common.base.utils.MD5;
import com.clay.common.base.utils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户控制器
 *
 * @author clay
 */
@RestController
@RequestMapping("/admin/acl/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@ApiOperation(value = "获取用户信息")
	@GetMapping("/get/{id}")
	public R get(@ApiParam(name = "id", value = "用户ID", required = true) @PathVariable String id) {
		User user = userService.getById(id);
		return R.ok().data("item", user);
	}

	@ApiOperation(value = "获取管理用户分页列表")
	@GetMapping("{page}/{limit}")
	public R index(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
			@ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
			@ApiParam(name = "courseQuery", value = "查询对象", required = false) User userQueryVo) {
		Page<User> pageParam = new Page<>(page, limit);
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(userQueryVo.getUsername())) {
			wrapper.like("username", userQueryVo.getUsername());
		}

		IPage<User> pageModel = userService.page(pageParam, wrapper);
		return R.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
	}

	@ApiOperation(value = "新增管理用户")
	@PostMapping("save")
	public R save(@RequestBody User user) {
		user.setPassword(MD5.encrypt(user.getPassword()));
		userService.save(user);
		return R.ok();
	}

	@ApiOperation(value = "根据用户获取角色数据")
	@GetMapping("/toAssign/{userId}")
	public R toAssign(@PathVariable String userId) {
		Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
		return R.ok().data(roleMap);
	}

	@ApiOperation(value = "根据用户分配角色")
	@PostMapping("/doAssign")
	public R doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
		roleService.saveUserRoleRealtionShip(userId, roleId);
		return R.ok();
	}

}
