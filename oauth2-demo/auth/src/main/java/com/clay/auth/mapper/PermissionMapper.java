package com.clay.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clay.common.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取用户的权限列表
     *
     * @param userId
     * @return
     */
    @Select("SELECT" +
            " id, code, description, url, create_time " +
            " FROM" +
            " t_permission " +
            " WHERE" +
            " id IN (" +
            " SELECT permission_id FROM t_role_permission WHERE role_id IN (" +
            " SELECT role_id FROM t_user_role WHERE user_id = #{userId} ) )")
    List<Permission> selectPermissionsByUserId(@Param("userId") Long userId);

}
