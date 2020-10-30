package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.models.Employee;

public class EmployeeServices {
	private static EmployeeDao ed = new EmployeePostgres();

	public List<Employee> getAllEmployees() {
		return ed.getEmployees();
	}

	public Employee getEmployee(int id) {
		return ed.getEmployeeById(id);
	}

	public boolean createEmployee(Employee e) {

		if (ed.createEmployee(e) == 1) {
			return true;
		}

		return false;
	}

	public boolean updateEmployee(Employee e) {

		if (ed.updateEmployee(e) == 1) {
			return true;
		}

		return false;
	}
	
	public boolean deleteEmployee(int id) {

		if (ed.deleteEmployee(id) == 1) {
			return true;
		}

		return false;
	}

}
