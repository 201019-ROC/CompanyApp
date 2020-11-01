package com.revature;

import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	private static EmployeeService es = new EmployeeService();

	private static Employee e = new Employee("Tom", 5225.5,  "Position ", 5, 5);
	public static void main(String[] args) {
		
		try {

			Connection c = ConnectionUtil.getConnectionFromFile();
			String driverName = c.getMetaData().getDriverName();
			System.out.println(driverName);

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(e.getDepartment().getId());
//		System.out.println(es.getEmployeeById(1));


//			System.out.println(ds.getAllDepartments());
//		System.out.println(ds.getDepartmentById(3));

//		Department d = new Department("Coffee", 5000);
//		System.out.println(ds.createDepartment(d));
//		System.out.println(ds.getAllDepartments());

//		Employee e = new Employee("Sam", 5080, "Chief", 2, 2);
//		System.out.println(es.createEmployee(e));


//		Department d1 = ds.getDepartmentById(1);
//		d1.setMonthlyBudget(25000);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(13));

//		Employee e1 = es.getEmployeeById(3);
//		e1.setName("Kathy");
//		es.updateEmployee(e1);
//		System.out.println(es.getEmployeeById(3));
		
//		System.out.println(ds.deleteDepartment(20));
//		System.out.println(es.deleteEmployee(16));
//		System.out.println(es.getEmployeeById(17));
//
//		System.out.println(ds.getDepartmentById(1));
//		ds.budgetIncrease(500, 2);
//		System.out.println(ds.getDepartmentById(1));

		System.out.println(es.getEmployees());

	}


}
