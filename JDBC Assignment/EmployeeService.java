package com.revature.services;

import java.io.Serializable;

public class EmployeeService implements Serializable {

	private static final long serialVersionUID = 1L;
	private int empl_id;
	private String name;
	private double monthlySalary;
	private String position;
	private int manager_id;
	private int dept_id;
	public int getEmpl_id() {
		return empl_id;
	}
	public void setEmpl_id(int empl_id) {
		this.empl_id = empl_id;
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
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	@Override
	public String toString() {
		return "Employee [empl_id=" + empl_id + ", name=" + name + ", monthlySalary=" + monthlySalary + ", position="
				+ position + ", manager_id=" + manager_id + ", dept_id=" + dept_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dept_id;
		result = prime * result + empl_id;
		result = prime * result + manager_id;
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
		EmployeeService other = (EmployeeService) obj;
		if (dept_id != other.dept_id)
			return false;
		if (empl_id != other.empl_id)
			return false;
		if (manager_id != other.manager_id)
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


}
