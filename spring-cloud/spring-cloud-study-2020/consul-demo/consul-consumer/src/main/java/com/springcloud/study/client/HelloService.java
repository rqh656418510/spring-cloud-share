package com.springcloud.study.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-provider")
public interface HelloService {

    @RequestMapping(value = "/provider/sayHello", method = RequestMethod.GET)
    public String sayHello(@RequestParam("name") String name);

}
