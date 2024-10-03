package com.seata.study.dao;

import com.seata.study.domain.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author clay
 * @version 1.0
 */
@Mapper
public interface StorageMapper {

    public Storage findByProduct(@Param("productId") Long productId);

    public void update(Storage storage);

}
