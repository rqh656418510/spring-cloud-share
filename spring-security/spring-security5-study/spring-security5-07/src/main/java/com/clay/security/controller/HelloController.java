package com.clay.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class HelloController {

    @PreAuthorize("hasRole('ROLE_sale')")
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Security";
    }

    @PreAuthorize("hasAnyRole('ROLE_sale,ROLE_admin')")
    @RequestMapping("/hello2")
    public String hello2() {
        return "Hello Spring Security";
    }

    @PreAuthorize("hasAuthority('manager')")
    @RequestMapping("/goodbye")
    public String goodbye() {
        return "Goodbye Spring Security";
    }

    @PreAuthorize("hasAnyAuthority('manager,hr')")
    @RequestMapping("/goodbye2")
    public String goodbye2() {
        return "Goodbye Spring Security";
    }

}
