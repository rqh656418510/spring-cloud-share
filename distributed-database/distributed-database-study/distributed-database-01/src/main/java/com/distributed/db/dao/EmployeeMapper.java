package com.distributed.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.distributed.db.domain.Employee;

@Mapper
public interface EmployeeMapper {

	public List<Employee> queryAll();

	public boolean delEmpById(Long id);

}