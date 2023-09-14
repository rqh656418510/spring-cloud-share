package com.clay.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clay.security.entity.Permission;
import com.clay.security.entity.Role;
import com.clay.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author clay
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户 ID 查询角色
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(@Param("userId") Long userId);

    /**
     * 根据用户 ID 查询菜单
     * @param userId
     * @return
     */
    List<Permission> selectMenuById(@Param("userId") Long userId);

}
