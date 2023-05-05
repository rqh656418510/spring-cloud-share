package com.clay.h2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clay.h2.entity.User;
import com.clay.h2.utils.Result;

/**
 * @author clay
 * @date 2023-05-05
 */
public interface UserService extends IService<User> {

	/**
	 * 分页查询
	 * @return
	 */
	Result getByPage();

	/**
	 * 新增记录
	 * @param user
	 * @return
	 */
	Result add(User user);

	/**
	 * 清空数据
	 * @return
	 */
	Result clear();

}
