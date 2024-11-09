package com.clay.mapper;

import com.clay.entities.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface StorageMapper {

    /**
     * 获取库存
     */
    Storage selectByProductId(@Param("productId") Long productId);

    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

}