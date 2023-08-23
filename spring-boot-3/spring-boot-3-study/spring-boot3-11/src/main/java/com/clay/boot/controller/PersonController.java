package com.clay.boot.controller;

import com.clay.boot.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/save")
    public String save() {
        Person person = new Person();
        person.setId(1L);
        person.setAge(18);
        person.setRole("admin");
        person.setUserName("Tom");
        person.setEmail("example@gmail.com");
        redisTemplate.opsForValue().set("person", person);
        return "success";
    }

    @GetMapping("/get")
    public Person get() {
        return (Person) redisTemplate.opsForValue().get("person");
    }

}
