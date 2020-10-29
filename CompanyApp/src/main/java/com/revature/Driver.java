package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			Connection c = ConnectionUtil.getConnection();
			String driverName = c.getMetaData().getDriverName();
			System.out.println(driverName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
