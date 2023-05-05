package com.clay.h2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clay.h2.entity.User;

/**
 * @author clay
 * @date 2023-05-05
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 清空表
	 */
	void clear();

}
