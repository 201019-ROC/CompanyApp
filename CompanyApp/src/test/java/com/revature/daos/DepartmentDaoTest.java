package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoTest {

	private DepartmentDao dd = new DepartmentPostgres();
	
	@BeforeClass
	public static void setUp() {
		try (Connection c = ConnectionUtil.getH2Connection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDepartmentByIdTest() {
		assertEquals(new Department(1, "HR", 5000), dd.getDepartmentById(1));
	}
	
	@Test
	public void getDepartmentByInvalidIdTest() {
		assertNull(dd.getDepartmentById(100));
	}
	
	@Test
	public void createDepartmentTest() {
		assertEquals(1, dd.createDepartment(new Department("New Department", 100)));
	}
	
	@Test 
	public void updateDepartmentTest(){
		
		assertEquals(1, dd.updateDepartment(new Department(1, "HR", 5001)));
	}
	
	@AfterClass
	public static void tearDown() {
		try (Connection c = ConnectionUtil.getH2Connection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
