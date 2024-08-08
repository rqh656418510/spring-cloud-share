package com.clay.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(@RequestParam(name = "name", required = false) String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            name = "Thymeleaf";
        }
        model.addAttribute("name", name);
        model.addAttribute("imgShow", true);
        model.addAttribute("imgUrl", "/static/background.jpg");
        model.addAttribute("imgStyle", "width:400px; height: 300px");
        return "welcome";
    }

}
