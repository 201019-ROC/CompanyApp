package com.revature.daos;

import java.util.List;

import com.revature.models.Department;

public interface DepartmentDao {
	
	public int createDepartment(Department d);
	public List<Department> getDepartments();
	public Department getDepartmentById(int id);	
	public int updateDepartment(Department d);
	public int deleteDepartment(int id);
	public void budgetIncrease(double increase, int id);
	
}
