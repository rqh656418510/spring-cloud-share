package com.distributed.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.distributed.db.domain.Employee;
import com.distributed.db.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public ModelAndView list() {
		List<Employee> list = this.employeeService.list();
		ModelAndView view = new ModelAndView("/employee/list", "employees", list);
		return view;
	}

	@ResponseBody
	@RequestMapping("/delete/{id}")
	public boolean deleteEmployee(@PathVariable("id") Long id) {
		return this.employeeService.deleteEmployee(id);
	}

}