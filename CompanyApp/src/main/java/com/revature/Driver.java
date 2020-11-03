package com.revature;

import com.revature.daos.EmployeePostgres;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	
	public static void main(String[] args) {
		
//		try {
//			
//			Connection c = ConnectionUtil.getConnection();
//			String driverName = c.getMetaData().getDriverName();
//			System.out.println(driverName);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	

//		System.out.println(ds.getAllDepartments());		
//		System.out.println(ds.getDepartmentById(5));
		
//		Department d = new Department("Coffee", 5000);
//		System.out.println(ds.createDepartment(d));
//		System.out.println(ds.getAllDepartments());
		
//		Department d1 = ds.getDepartmentById(4);
//		d1.setMonthlyBudget(200);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(4));
		
		
//		System.out.println(ds.getDepartmentById(1));		
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(1));
		
		EmployeePostgres ep = new EmployeePostgres();
		
		System.out.println(ep.getEmployees());
//		System.out.println(ep.getEmployeeById(2));
		
//		System.out.println(ep.getEmployees());
//		Department dept = ds.getDepartmentById(3);
//		Employee e = new Employee(69, "Alfred", 69.69, "Clerk", 3, dept);
//		System.out.println(ep.createEmployee(e));
//		System.out.println(ep.getEmployees());
		
//		System.out.println(ep.getEmployees());
//		Department dept = ds.getDepartmentById(2);
//		Employee e = new Employee(14, "Alfred", 699.69, "Clerk", 3, dept);
//		System.out.println(ep.updateEmployee(e));
//		System.out.println(ep.getEmployees());

		ep.deleteEmployee(14);
		ep.deleteEmployee(15);
		ep.deleteEmployee(16);
		System.out.println(ep.getEmployees());
	}
}
