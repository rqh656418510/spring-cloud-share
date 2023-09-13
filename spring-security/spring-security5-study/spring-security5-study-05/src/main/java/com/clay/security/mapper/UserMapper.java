package com.clay.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clay.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author clay
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
