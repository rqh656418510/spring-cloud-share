package com.seata.study.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 *
 * @author clay
 * @version 1.0
 */
@Configuration
@MapperScan("com.seata.study.dao")
public class MyBatisConfig {

}