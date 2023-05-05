package com.clay.h2.controller;

import com.clay.h2.entity.User;
import com.clay.h2.service.UserService;
import com.clay.h2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 * @date 2023-05-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/page")
	public Result getByPage() {
		return userService.getByPage();
	}

	@PostMapping("/add")
	public Result add(@RequestBody User user) {
		return userService.add(user);
	}

	@DeleteMapping("/clear")
	public Result clear() {
		return userService.clear();
	}

}
