package com.distributed.db.service.impl;

import com.distributed.db.bean.Employee;
import com.distributed.db.dao.EmployeeDao;
import com.distributed.db.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> list() {
        return this.employeeDao.queryAll();
    }

    @Override
    public boolean delete(Long id) {
        return this.employeeDao.delete(id);
    }

}
