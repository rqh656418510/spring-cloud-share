package com.clay.boot.web.controller;

import com.clay.boot.web.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    @ResponseBody
    @GetMapping("/get")
    public Person get() {
        Person person = new Person();
        person.setId(1L);
        person.setAge(18);
        person.setRole("admin");
        person.setUserName("张三");
        person.setEmail("aaa@qq.com");
        return person;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1L, "Peter1", "peter@gmail.com", 18, "pm"));
        persons.add(new Person(2L, "Peter2", "peter@gmail.com", 16, "admin"));
        persons.add(new Person(3L, "Peter3", "peter@gmail.com", 18, "pm"));
        persons.add(new Person(4L, "Peter4", "peter@gmail.com", 17, "hr"));
        persons.add(new Person(5L, "Peter5", "peter@gmail.com", 18, "boss"));
        model.addAttribute("persons", persons);
        return "list";
    }

}
