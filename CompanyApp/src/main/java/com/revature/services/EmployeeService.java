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
	
	public Employee getDepartmentById(int id) {
		return ee.getEmployeeById(id);
	}
	
	public boolean createDepartment(Employee e) {
		int deptCreated = ee.createEmployee(e);
		if(deptCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateDepartment(Employee e) {
		int deptupdated = ee.updateEmployee(e);
		if(deptupdated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteDepartment(int id) {
		int deptDeleted = ee.deleteEmployee(id);
		if(deptDeleted != 0) {
			return true;
		} else {
			return false;
		}
	}
}
