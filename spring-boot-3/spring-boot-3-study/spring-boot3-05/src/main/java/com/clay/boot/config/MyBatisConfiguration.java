package com.clay.boot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author clay
 */
@Configuration
@MapperScan(basePackages = "com.clay.boot.mapper")
public class MyBatisConfiguration {

}
