package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.models.Department;
import com.revature.models.Employee;

public class EmployeeService {
private static EmployeeDao ed = new EmployeePostgres();
	
	public List<Employee> getAllEmployees(){
		return ed.getEmployees();
	}
	
	public Employee getEmployeeById(int id) {
		return ed.getEmployeeById(id);
	}
	
	public boolean createEmployee(Employee e) {
		int emplCreated = ed.createEmployee(e);
		if(emplCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateEmployee(Employee e) {
		int emplUpdated = ed.updateEmployee(e);
		if(emplUpdated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteEmployee(int id) {
		int emplDeleted = ed.deleteEmployee(id);
		if(emplDeleted != 0) {
			return true;
		} else {
			return false;
		}
	}
}
