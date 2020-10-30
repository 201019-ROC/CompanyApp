package com.revature;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeService es = new EmployeeService();
	
	public static void main(String[] args) {
		
//		try {
//			
//			Connection c = ConnectionUtil.getConnectionFromFile();
//			String driverName = c.getMetaData().getDriverName();
//			System.out.println(driverName);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	

//		System.out.println(ds.getAllDepartments());
//		System.out.println(ds.getDepartmentById(1));
		
//		Department d = new Department("Coffee", 5000);
//		System.out.println(ds.createDepartment(d));
//		System.out.println(ds.getAllDepartments());
		
//		Department d1 = ds.getDepartmentById(5);
//		d1.setMonthlyBudget(200);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(13));
		
//		System.out.println(ds.deleteDepartment(13));
		
//		System.out.println(ds.getDepartmentById(1));
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(1));
		
		// Test Print all Employee
		System.out.println(es.getAllEmployee());
		
		// Test Print get Employee by Id
		System.out.println(es.getEmployeeById(1));
		
		// Test print create an Employee
		Employee a = new Employee("Mathrew",3400, "Intern", 1, 1);
		System.out.println(es.createEmployee(a));
		System.out.println(es.getAllEmployee());
		
		// Test print update an Employee
		System.out.println(es.getEmployeeById(1));
		Employee e1 = es.getEmployeeById(1);
		e1.setDepartmentId(3);
		es.updateEmployee(e1);
		System.out.println(es.getEmployeeById(1));
		
		// Test print delete an Employee
		System.out.println(es.deleteEmployee(20));
		
	}

}
