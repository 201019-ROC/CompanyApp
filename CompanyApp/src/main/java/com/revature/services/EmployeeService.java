package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.models.Employee;

public class EmployeeService {

	private static EmployeeDao ee = new EmployeePostgres();

	public List<Employee> getEmployees() {
		return ee.getEmployees();
	}

	public Employee getEmployeeById(int id) {
		return ee.getEmployeeById(id);
	}

	public int createEmployee(Employee e) {
		return ee.createEmployee(e);
	}

	public int updateEmployee(Employee e) {
		return ee.updateEmployee(e);
	}

	public int deleteEmployee(int id) {
		return ee.deleteEmployee(id);
	}
}
