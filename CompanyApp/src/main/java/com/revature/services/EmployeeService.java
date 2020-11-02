package com.revature.services;

import java.util.List;
import com.revature.daos.*;
import com.revature.models.*;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;

// from EmployeePostgres.java on methods
public class EmployeeService {
	private static EmployeeDao ed = new EmployeePostgres();
	
	public List<Employee> getAllEmployees(){
		return ed.getEmployees();
	}
	
	public Employee getEmployeeById(int id) {
		return ed.getEmployeeById(id);
	}
	
	public boolean createEmployee(Employee d) {
		int empCreated = ed.createEmployee(d);
		if(empCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateEmployee(Employee d) {
		int empupdated = ed.updateEmployee(d);
		if(empupdated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteEmployee(int id) {
		int empDeleted = ed.deleteEmployee(id);
		if(empDeleted != 0) {
			return true;
		} else {
			return false;
		}
	}
	

}
