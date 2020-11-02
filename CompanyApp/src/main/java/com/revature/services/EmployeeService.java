package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeePostgres;
import com.revature.models.Department;
import com.revature.models.Employee;

	public class EmployeeService {
		private static EmployeeDao ed = new EmployeePostgres();
		
		public List<Employee> getAllEmployee(){
			return ed.getEmployees();
		}
		
		public Employee getEmployeeById(int id){
			return ed.getEmployeeById(id);
		}
		
		public boolean createEmployee(Employee a) {
			int emplCreated = ed.createEmployee(a);
			if(emplCreated != 0) {
				return true;
			} else {
				return false;
			}
		}
		
		public boolean updateEmployee(Employee a) {
			int emplUpdated = ed.updateEmployee(a);
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
