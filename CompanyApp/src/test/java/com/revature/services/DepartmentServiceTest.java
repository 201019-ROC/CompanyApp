package com.revature.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.DepartmentDao;
import com.revature.models.Department;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
	
	@Mock
	private DepartmentDao dd;
	
	@InjectMocks
	private DepartmentService ds;

	
	@Test
	public void getDepartmentByValidIdTest() {
		Mockito.when(dd.getDepartmentById(1)).thenReturn(new Department(1, "HR", 7500));
		Department expected = new Department(1, "HR", 7500);
		assertEquals(expected, ds.getDepartmentById(1));
	}
}
