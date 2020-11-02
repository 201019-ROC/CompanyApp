package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Department;
import com.revature.services.DepartmentService;
import com.revature.util.ConnectionUtil;

public class Driver {

	private static DepartmentService ds = new DepartmentService();
	
	public static void main(String[] args) throws IOException {
		
		try {
			
			Connection c = ConnectionUtil.getConnectionFromFile();
			String driverName = c.getMetaData().getDriverName();
			System.out.println(driverName);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

//		System.out.println(ds.getAllDepartments());
//		System.out.println(ds.getDepartmentById(1));
		
//		Department d = new Department("Coffee", 5000);
//		System.out.println(ds.createDepartment(d));
//		System.out.println(ds.getAllDepartments());
//		
//		Department d1 = ds.getDepartmentById(3);
//		d1.setMonthlyBudget(200);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(3));
//		
//		System.out.println(ds.deleteDepartment(6));
//		System.out.println(ds.getAllDepartments());
//		
//		System.out.println(ds.getDepartmentById(1));
//		ds.budgetIncrease(500, 1);
//		System.out.println(ds.getDepartmentById(1));
	}

}
