package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import com.revature.daos.DepartmentDao;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double monthlySalary;
	private String position;
	private int managerId;
	private int departmentId;
	Department department = new Department();
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", name=" + name + ", monthlySalary=" + monthlySalary + ", position=" + position
//				+ ", managerId=" + managerId + ", department=" + departmentId + "]";
//	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", monthlySalary=" + monthlySalary + ", position=" + position
				+ ", managerId=" + managerId + ", department=" + department + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + departmentId;
		result = prime * result + id;
		result = prime * result + managerId;
		long temp;
		temp = Double.doubleToLongBits(monthlySalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (departmentId != other.departmentId)
			return false;
		if (id != other.id)
			return false;
		if (managerId != other.managerId)
			return false;
		if (Double.doubleToLongBits(monthlySalary) != Double.doubleToLongBits(other.monthlySalary))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	public Employee(int id, String name, double monthlySalary, String position, int managerId, int departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.monthlySalary = monthlySalary;
		this.position = position;
		this.managerId = managerId;
		this.departmentId = departmentId;
	}
	
	public Employee(String name, double monthlySalary, String position, int managerId, int departmentId) {
		super();
		this.name = name;
		this.monthlySalary = monthlySalary;
		this.position = position;
		this.managerId = managerId;
		this.departmentId = departmentId;
	}
	
	public Employee(int id, String name, double monthlySalary, String position, int managerId, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.monthlySalary = monthlySalary;
		this.position = position;
		this.managerId = managerId;
		this.department = department;
	}
		
	public int getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	
	
}
