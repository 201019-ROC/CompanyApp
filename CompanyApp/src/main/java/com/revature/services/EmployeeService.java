package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.models.Employee;

public class EmployeeService {
	private static EmployeeDao ee = new EmployeePostgres();
	
	public List<Employee> getAllEmployee(){
		return ee.getEmployees();
	}
	
	public Employee getEmployeeById(int id){
		return ee.getEmployeeById(id);
	}
	
	public boolean createEmployee(Employee a) {
		int emplCreated = ee.createEmployee(a);
		if(emplCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateEmployee(Employee a) {
		int emplUpdated = ee.updateEmployee(a);
		if(emplUpdated != 0) {
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


