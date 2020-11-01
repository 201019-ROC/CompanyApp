package com.revature.daos;

 import com.revature.models.Employee;

import java.util.List;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	 public Employee getEmployeeById(int id);
	 public int createEmployee(Employee e);
	 public int updateEmployee(Employee e);
	 public int deleteEmployee(int id);

}
