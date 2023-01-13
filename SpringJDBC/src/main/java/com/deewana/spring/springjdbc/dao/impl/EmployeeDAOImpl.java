package com.deewana.spring.springjdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.deewana.spring.springjdbc.dao.EmployeeDAO;
import com.deewana.spring.springjdbc.entity.Employee;

@Component("employeeDao")
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int createRecord(Employee employee) {
		KeyHolder holder=new GeneratedKeyHolder();
		final String name=employee.getName();
		final String location=employee.getLocation();
		final String dept=employee.getDept();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query="insert into employee (name,location,dept)values (?,?,?)";
				PreparedStatement pst=con.prepareStatement(query,java.sql.Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, name);
				pst.setString(2, location);
				pst.setString(3, dept);

				return pst;
			}
		},holder);
		int userId=holder.getKey().intValue();
		return userId;
	}

	@Override
	public int updateRecord(int id) {
		String updatedDept="Development";
		String query="update employee set dept=? where id=?";
		int result=jdbcTemplate.update(query,updatedDept,id);
		return result;
	}

	@Override
	public int deleteRecord(int id) {
		String query="delete from employee where id=?";
		int result=jdbcTemplate.update(query,id);
		return result;
	}

	@Override
	public List<Employee> getRecords() {
		Employee emp=new Employee();
		String query="select * from employee";
		List<Employee> results = jdbcTemplate.query(query, emp);
		return results;
	}

}
