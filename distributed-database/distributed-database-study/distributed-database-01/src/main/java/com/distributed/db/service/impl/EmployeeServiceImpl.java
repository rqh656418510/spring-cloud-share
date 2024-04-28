package com.distributed.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributed.db.bean.Employee;
import com.distributed.db.dao.EmployeeMapper;
import com.distributed.db.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<Employee> list() {
		return this.employeeMapper.queryAll();
	}

	@Override
	public boolean deleteEmployee(Long id) {
		return this.employeeMapper.delEmpById(id);
	}

}
