package com.springcloud.study.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<String> {

    private String userName;
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserCommand.class);
    private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("CommandKey");

    public UserCommand(String userName, RestTemplate restTemplate) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CacheGroup")).andCommandKey(KEY));
        this.userName = userName;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        String result = restTemplate.getForObject("http://PROVIDER/user/getUser?userName={1}", String.class, this.userName);
        logger.info(result);
        return result;
    }

    @Override
    protected String getFallback() {
        return super.getFallback();
    }

    @Override
    protected String getCacheKey() {
        return this.userName;
    }

    public static void cleanCache(String userName) {
        HystrixRequestCache.getInstance(KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(userName);
    }
}
