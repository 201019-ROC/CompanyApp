package com.revature;

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
//		System.out.println(ds.getDepartmentById(1));
		
//		Department d = new Department("Coffee", 5000);
//		System.out.println(ds.createDepartment(d));
//		System.out.println(ds.getAllDepartments());
		
//		Department d1 = ds.getDepartmentById(13);
//		d1.setMonthlyBudget(200);
//		ds.updateDepartment(d1);
//		System.out.println(ds.getDepartmentById(13));
		
//		System.out.println(ds.deleteDepartment(13));
		
		System.out.println(ds.getDepartmentById(1));
		ds.budgetIncrease(500, 1);
		System.out.println(ds.getDepartmentById(1));
	}

}
