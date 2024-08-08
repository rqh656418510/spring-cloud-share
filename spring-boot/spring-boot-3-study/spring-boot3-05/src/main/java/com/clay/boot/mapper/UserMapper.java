package com.clay.boot.mapper;

import com.clay.boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author clay
 */
@Mapper
public interface UserMapper {

    User getById(@Param("id") Long id);

}
