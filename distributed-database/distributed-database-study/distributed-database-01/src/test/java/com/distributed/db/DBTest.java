package com.distributed.db;

import com.distributed.db.bean.Employee;
import com.distributed.db.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class DBTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void insert() {
        Employee employee = new Employee();
        employee.setGender("M");
        employee.setLastName("Peter");
        employee.setEmail("Peter@gmail.com");
        employeeService.add(employee);
    }

}
