package com.revature;

import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeService es = new EmployeeService();

	public static void main(String[] args) {
//		
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

//		Department d1 = ds.getDepartmentById(4);
//		d1.setMonthlyBudget(200);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(4));

//		System.out.println(d/s.deleteDepartment(4));

//		System.out.println(ds.getDepartmentById(1));
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(1));

// ************************ EMPLOYEE ************************
		
	// Get list of employees
//		System.out.println(es.getAllEmployee());
		
	// Get employee by id
//		System.out.println(es.getEmployeeById(5));
		
	// Create new employee
//		Employee e = new Employee("Alex", 5000, "Java developer", 2, 2);
//		System.out.println(es.createEmployee(e));
//		System.out.println(es.getAllEmployee());
//		System.out.println(es.getEmployeeById(5));
		
	// Update information about employee
//		Employee e1 = es.getEmployeeById(1);
//		e1.setName("Emy");
//		e1.setDepartmentId(1);
//		es.updateEmployee(e1);
//		System.out.println(es.getEmployeeById(1));
		
	// Delete an employee
//		System.out.println(es.getAllEmployee());
//		System.out.println(es.deleteEmployee(17));
//		System.out.println(es.getAllEmployee());
		
	//give prize (% of salary) for each employee in department
	//prizeEmployee(the name of department, percentage of salary)
//		System.out.println(es.getAllEmployee());
//		es.prizeEmployee("Accounting", 25);
//		System.out.println(es.getAllEmployee());
		
	}

}
