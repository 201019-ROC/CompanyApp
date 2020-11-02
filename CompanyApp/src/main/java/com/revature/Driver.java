package com.revature;



import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;
import com.revature.util.ConnectionUtil;


public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeService es = new EmployeeService();	
	
	public static void main(String[] args) {
		
		try {
			
		Connection c = ConnectionUtil.getConnectionFromFile();
			String driverName = c.getMetaData().getDriverName();
			System.out.println(driverName);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	

//		System.out.println(ds.getAllDepartments());
//		System.out.println(ds.getDepartmentById(1));
		
//		Department d = new Department("Coffee", 5000);
//		System.out.println(ds.createDepartment(d));
//		System.out.println(ds.getAllDepartments());
		
//		Department d1 = ds.getDepartmentById(5);
//		d1.setMonthlyBudget(900);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(5));
		
//		System.out.println(ds.deleteDepartment(13));
		
//		System.out.println(ds.getDepartmentById(5));
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(5));
		
//		System.out.println(es.getAllEmployees());
//		System.out.println(es.getEmployeeById(31));
//		
//		Employee e = new Employee(3, "Gator", 5555, "Cool Guy", 6);	
//		System.out.println(es.createEmployee(e));
//		System.out.println(es.getAllEmployees());
		
		Employee e = es.getEmployeeById(33);
		e.setMonthlySalary(6000);
		es.updateEmployee(e);
		System.out.println(es.getEmployeeById(33));
		
//		System.out.println(es.deleteEmployee(3));
	}

}
