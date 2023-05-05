package com.clay.h2.service;

import com.clay.h2.entity.User;
import com.clay.h2.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class UserServiceImpl {

	@Autowired
	private UserService userService;

	@Test
	public void getByPage() {
		Result result = userService.getByPage();
		List<User> userList = (List<User>) result.getData();
		System.out.println(userList);
	}

	@Test
	@Rollback(false)
	public void add() {
		User user = new User();
		user.setUsername("LiSi");
		user.setPwd("123456");
		userService.add(user);
	}

	@Test
	@Rollback(false)
	public void clear() {
		userService.clear();
	}

}
