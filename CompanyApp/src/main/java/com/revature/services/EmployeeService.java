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
		int deptCreated = ee.createEmployee(e);
		if(deptCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateEmployee(Employee e) {
		int deptupdated = ee.updateEmployee(e);
		if(deptupdated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteEmployee(int id) {
		int deptDeleted = ee.deleteEmployee(id);
		if(deptDeleted != 0) {
			return true;
		} else {
			return false;
		}
	}
}
