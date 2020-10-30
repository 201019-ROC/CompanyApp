package com.revature;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;

public class Driver {

	private static EmployeeService es = new EmployeeService();
	private static DepartmentService ds = new DepartmentService();
	
	public static void main(String[] args) {
//		System.out.println(es.getAllEmployees());
//		System.out.println(es.getEmployeeById(1));
		
//		Department fun = ds.getDepartmentById(1);
//		Employee e1 = new Employee("Dwight Schroot", 2500.0, "Assistant to the Regional manager", 1, fun);
//		e1.setId(14);
//		System.out.println(es.createEmployee(e1));

//		e1.setMonthlySalary(4000.0);
//		System.out.println(es.updateEmployee(e1));
		
		System.out.println(es.deleteEmployee(14));
	}

}
