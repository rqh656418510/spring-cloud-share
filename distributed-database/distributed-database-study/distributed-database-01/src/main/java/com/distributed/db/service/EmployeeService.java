package com.distributed.db.service;

import java.util.List;

import com.distributed.db.domain.Employee;

public interface EmployeeService {

	public List<Employee> list();

	public boolean deleteEmployee(Long id);

}
