package com.clay.acl.controller;

import com.clay.acl.entity.Permission;
import com.clay.acl.entity.User;
import com.clay.acl.service.PermissionService;
import com.clay.common.base.utils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限菜单管理
 *
 * @author clay
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@ApiOperation(value = "获取菜单信息")
	@GetMapping("/get/{id}")
	public R get(@ApiParam(name = "id", value = "菜单ID", required = true) @PathVariable String id) {
		Permission permission = permissionService.getById(id);
		return R.ok().data("item", permission);
	}

	@ApiOperation(value = "查询所有菜单")
	@GetMapping
	public R indexAllPermission() {
		List<Permission> list = permissionService.queryAllMenu();
		return R.ok().data("children", list);
	}

	@ApiOperation(value = "给角色分配权限")
	@PostMapping("/doAssign")
	public R doAssign(String roleId, String[] permissionId) {
		permissionService.saveRolePermissionRealtionShipGuli(roleId, permissionId);
		return R.ok();
	}

	@ApiOperation(value = "根据角色获取菜单")
	@GetMapping("toAssign/{roleId}")
	public R toAssign(@PathVariable String roleId) {
		List<Permission> list = permissionService.selectAllMenu(roleId);
		return R.ok().data("children", list);
	}

}
