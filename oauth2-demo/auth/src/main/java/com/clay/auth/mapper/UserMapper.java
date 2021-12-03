package com.clay.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clay.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
