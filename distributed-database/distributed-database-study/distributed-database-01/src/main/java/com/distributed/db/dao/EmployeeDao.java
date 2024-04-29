package com.distributed.db.dao;

import com.caland.sun.client.SunSqlMapClientTemplate;
import com.distributed.db.bean.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class EmployeeDao {

    @Resource
    SunSqlMapClientTemplate sqlMapClientTemplate;

    public void insert(Employee employee) {
        this.sqlMapClientTemplate.insert("Employee.insert", employee);
    }

    public List<Employee> queryAll() {
        return (List<Employee>) this.sqlMapClientTemplate.queryForList("Employee.queryAll");
    }

    public boolean delete(Long id) {
        return this.sqlMapClientTemplate.delete("Employee.deleteById") > 0;
    }

}