package com.distributed.db.service;

import com.distributed.db.bean.Employee;

import java.util.List;

public interface EmployeeService {

    void add(Employee employee);

    List<Employee> list();

    boolean delete(Long id);

}
