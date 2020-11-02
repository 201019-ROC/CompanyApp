package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.daos.DepartmentPostgres;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;
import com.revature.util.ConnectionUtil;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeService es=new EmployeeService();
	
	
	public static void main(String[] args) {
		
		try {
			
			Connection c = ConnectionUtil.getConnection();
			String driverName = c.getMetaData().getDriverName();
			//System.out.println(driverName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

//		System.out.println(ds.getAllDepartments());
//		System.out.println(ds.getDepartmentById(1));
		
//		Department d = new Department("Coffee", 5000);
//		System.out.println(ds.createDepartment(d));
//		System.out.println(ds.getAllDepartments());
		
//		Department d1 = ds.getDepartmentById(13);
//		d1.setMonthlyBudget(200);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(13));
		
//		System.out.println(ds.deleteDepartment(13));
		
//		System.out.println(ds.getDepartmentById(1));
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(1));
		
///////////////////////////////
		
// my code for test
		
//////////////////////////////
		
		
		System.out.println(es.getAllEmployees());
 		System.out.println(es.getEmployeeById(1));
		
////	Create employee
//		//public Employee(int id, String name, double monthlySalary, String position, int managerId, Department department)	
		Department de1 = ds.getDepartmentById(1);
		Employee e = new Employee ("Amanda", 4692, "Administrative Assistant", 1, de1);
		System.out.println(es.createEmployee(e));
		System.out.println(es.getAllEmployees());
		
////	update emp in all fields
		System.out.println(es.getEmployeeById(15));
		Employee eu= es.getEmployeeById(15);
		eu.setName("Amy");
		eu.setMonthlySalary(4800);
		eu.setPosition("HR Manager");
		eu.setManagerId(3);
		eu.setDepartment(ds.getDepartmentById(3));
		System.out.println(es.updateEmployee(eu));
		System.out.println(es.getEmployeeById(15));
		
		System.out.println(es.deleteEmployee(15));
		System.out.println(es.getAllEmployees());
	}



}
