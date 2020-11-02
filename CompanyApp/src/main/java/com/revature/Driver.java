package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;
import com.revature.util.ConnectionUtil;


public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeService es = new EmployeeService();

	public static void main(String[] args) {
		
		try {
			
			Connection c = ConnectionUtil.getConnection();
			String driverName = c.getMetaData().getDriverName();
			System.out.println(driverName);
			
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
//		
//		System.out.println(ds.getDepartmentById(2));
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(1));
		System.out.println(es.getAllEmployees());
		System.out.println(es.getEmployeeById(1));
		Employee em= new Employee("Manaf", 2000, "IT spec", 4, 3);
		System.out.println(es.createEmployee(em));

		System.out.println(es.getEmployeeById(14));

		Employee emp= es.getEmployeeById(14);
		emp.setMonthlySalary(4000);
		es.updateEmployee(emp);
		System.out.println(es.getEmployeeById(14));

		
		System.out.println(es.deleteEmployee(10));
	}

}
