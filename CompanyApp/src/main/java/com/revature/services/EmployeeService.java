package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.models.Employee;

public class EmployeeService {
	private static EmployeeDao ee = new EmployeePostgres();
	
	public List<Employee> getAllEmployees(){
		return ee.getEmployees();
	}
	
	public Employee getEmployeeById(int id) {
		return ee.getEmployeeById(id);
	}
	
	public boolean createEmployee(Employee e) {
		int emplCreated = ee.createEmployee(e);
		if(emplCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateEmployee(Employee e) {
		int emplupdated = ee.updateEmployee(e);
		if(emplupdated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteEmployee(int id) {
		int emplDeleted = ee.deleteEmployee(id);
		if(emplDeleted != 0) {
			return true;
		} else {
			return false;
		}
	}
}
