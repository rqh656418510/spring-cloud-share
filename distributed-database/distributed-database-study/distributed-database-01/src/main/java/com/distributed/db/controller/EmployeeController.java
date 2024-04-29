package com.distributed.db.controller;

import com.distributed.db.bean.Employee;
import com.distributed.db.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @RequestMapping("/add")
    public boolean add() {
        Employee employee = new Employee();
        employee.setGender("M");
        employee.setLastName("Peter");
        employee.setEmail("Peter@gmail.com");
        employeeService.add(employee);
        return true;
    }

    @RequestMapping("/list")
    public ModelAndView list() {
        List<Employee> list = this.employeeService.list();
        ModelAndView view = new ModelAndView("/employee/list", "employees", list);
        return view;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public boolean deleteEmployee(@PathVariable("id") Long id) {
        return this.employeeService.delete(id);
    }

}