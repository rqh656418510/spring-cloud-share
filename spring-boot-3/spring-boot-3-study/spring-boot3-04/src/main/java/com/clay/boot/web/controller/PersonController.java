package com.clay.boot.web.controller;

import com.clay.boot.web.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonController {

    @ResponseBody
    @GetMapping("/get")
    public Person get() {
        Person person = new Person();
        person.setId(1L);
        person.setAge(18);
        person.setUserName("张三");
        person.setEmail("aaa@qq.com");
        return person;
    }

}
