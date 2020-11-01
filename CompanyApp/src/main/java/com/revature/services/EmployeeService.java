package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.models.Employee;

public class EmployeeService {
private static EmployeeDao ed = new EmployeePostgres();
	
	public List<Employee> getAllEmployees(){
		return ed.getEmployees();
	}
	
	public Employee getEmployeeById(int id) {
		return ed.getEmployeeById(id);
	}
	
	public boolean createEmployee(Employee empl) {
		int employeeCreated = ed.createEmployee(empl);
		if(employeeCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateEmployee(Employee empl) {
		int emploeeUpdated = ed.updateEmployee(empl);
		if(emploeeUpdated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteEmployee(int id) {
		int employeeDeleted = ed.deleteEmployee(id);
		if(employeeDeleted != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
