package com.distributed.db.service;

import com.distributed.db.bean.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> list();

    boolean delete(Long id);

}
