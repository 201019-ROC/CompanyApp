package com.revature;

import com.revature.exceptions.MonthlySalaryException;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeServices;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeServices es = new EmployeeServices();
	
	public static void main(String[] args) {
		
//		try {
//			
//			Connection c = ConnectionUtil.getHardCodedConnection();
//			String driverName = c.getMetaData().getDriverName();
//			System.out.println(driverName);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	

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
//		System.out.println(ds.getDepartmentById(1));
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(1));
		
		
		System.out.println(es.getAllEmployees());	
		System.out.println(es.getEmployee(13));
		
		try {
			System.out.println(es.createEmployee(new Employee("Hello", 9999.99, "Greeter", 1, new Department(1, "HR", 4000))));
		} catch (MonthlySalaryException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
		try {
			System.out.println(es.updateEmployee(new Employee(18, "Tim", 199.99, "Leader of lead", null, new Department(3, "Deparment of Fun", 4000))));
		} catch (MonthlySalaryException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(es.deleteEmployee(16));
	}

}
