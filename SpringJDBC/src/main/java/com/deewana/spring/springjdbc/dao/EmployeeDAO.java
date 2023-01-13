package com.deewana.spring.springjdbc.dao;

import java.util.List;

import com.deewana.spring.springjdbc.entity.Employee;

public interface EmployeeDAO {
	public int createRecord(Employee employee);

	public int updateRecord(int id);

	public int deleteRecord(int id);
	
	public List<Employee> getRecords();
}
