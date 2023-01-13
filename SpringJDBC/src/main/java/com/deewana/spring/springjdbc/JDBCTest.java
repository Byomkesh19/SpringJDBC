package com.deewana.spring.springjdbc;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deewana.spring.springjdbc.dao.EmployeeDAO;
import com.deewana.spring.springjdbc.entity.Employee;

public class JDBCTest {

	public static void main(String[] args) {
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("jdbcconfig.xml");
		EmployeeDAO employeeDao = (EmployeeDAO)context.getBean("employeeDao");
		Employee emp1=new Employee();
		emp1.setName("Byomkesh Choudhary");
		emp1.setLocation("Kolkata");
		emp1.setDept("Automation");

		int id=employeeDao.createRecord(emp1);
		System.out.println("the user id is : "+id);
		employeeDao.updateRecord(id);
//		employeeDao.deleteRescord(id);
		List<Employee> records = employeeDao.getRecords();
		System.out.println("Records : "+records);
		context.close();
	}

}
