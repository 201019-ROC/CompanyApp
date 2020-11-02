package com.revature;

import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeService es = new EmployeeService();
	
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
				
		System.out.println(es.getEmployees());
		System.out.println(es.getEmployeeById(1));
		
		Employee e = new Employee(10, "testname", 999, "testposition", 1, ds.getDepartmentById(1));
		System.out.println(es.createEmployee(e));
		System.out.println(es.getEmployees());
		
		e.setMonthlySalary(500);
		es.updateEmployee(e);
		System.out.println(es.getEmployeeById(10));
		
		System.out.println(es.getEmployees());
		System.out.println(es.deleteEmployee(10));		
		System.out.println(es.getEmployees());
		
		
	}

}
