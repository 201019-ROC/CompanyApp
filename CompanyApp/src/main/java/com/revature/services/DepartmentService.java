package com.revature.services;

import java.util.List;

import com.revature.daos.DepartmentDao;
import com.revature.daos.DepartmentPostgres;
import com.revature.models.Department;

public class DepartmentService {
	
	private DepartmentDao dd = new DepartmentPostgres();
	
	public List<Department> getAllDepartments(){
		return dd.getDepartments();
	}
	
	public Department getDepartmentById(int id) {
		return dd.getDepartmentById(id);
	}
	
	public boolean createDepartment(Department d) {
		int deptCreated = dd.createDepartment(d);
		if(deptCreated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateDepartment(Department d) {
		int deptupdated = dd.updateDepartment(d);
		if(deptupdated != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteDepartment(int id) {
		int deptDeleted = dd.deleteDepartment(id);
		if(deptDeleted != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void budgetIncrease(double increase, int id) {
		dd.budgetIncrease(increase, id);
	}
}
