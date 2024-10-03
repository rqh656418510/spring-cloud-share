package com.springcloud.study.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeptService {

    private static final Logger logger = LoggerFactory.getLogger(DeptService.class);

    @Autowired
    private RestTemplate restTemplate;

    @CacheResult
    @HystrixCommand
    public String getDept(String deptName) {
        String result = restTemplate.getForObject("http://PROVIDER/dept/getDept?deptName={1}", String.class, deptName);
        logger.info(result);
        return result;
    }

    @CacheResult
    @HystrixCommand(commandKey = "findDept")
    public String findDept(@CacheKey String deptName) {
        String result = restTemplate.getForObject("http://PROVIDER/dept/getDept?deptName={1}", String.class, deptName);
        logger.info(result);
        return result;
    }

    @CacheRemove(commandKey = "findDept")
    @HystrixCommand
    public String updateDept(@CacheKey String deptName) {
        logger.info("delete dept cache");
        return "update dept success";
    }

}
