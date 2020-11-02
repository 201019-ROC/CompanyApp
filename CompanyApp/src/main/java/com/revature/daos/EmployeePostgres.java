package com.revature.daos;


import java.math.BigDecimal;

import com.revature.models.Department;
import com.revature.util.ConnectionUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeePostgres implements EmployeeDao{

	//public Employee(int id, String name, double monthlySalary, String position, int managerId, Department department) 

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees = new ArrayList<>();
		String sql="select employees.empl_id, employees.empl_name,  employees.monthly_salary,  employees.empl_position,  employees,manager_id,  departments.dept_id, departments.dept_name, departments.monthly_budget \r\n"
				+ "from company.employees\r\n"
				+ "join company.departments\r\n"
				+ "on employees.dept_id = departments.dept_id;";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPosition= rs.getString("empl_position");
				int manangerId= rs.getInt("manager_id");
				int deptId= rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double monthlyBudget = rs.getDouble("monthly_budget");
				
				Department dept=new Department(deptId,deptName,monthlyBudget);
				
				employees.add(new Employee(emplId, emplName, monthlySalary, emplPosition,manangerId,dept));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;

	}

	@Override
	public Employee getEmployeeById(int id) {
		//public Employee(int id, String name, double monthlySalary, String position, int managerId, Department department)
		Employee empl=null;
		ResultSet rs = null;
		String sql = "select employees.empl_id, employees.empl_name,  employees.monthly_salary,  employees.empl_position,  employees,manager_id,  departments.dept_id, departments.dept_name, departments.monthly_budget \r\n"
				+ "from company.employees\r\n"
				+ "join company.departments\r\n"
				+ "on employees.dept_id = departments.dept_id where employees.empl_id = ? ;";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPosition= rs.getString("empl_position");
				int manangerId= rs.getInt("manager_id");
				int deptId= rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double monthlyBudget = rs.getDouble("monthly_budget");
				
				Department dept=new Department(deptId,deptName,monthlyBudget);
				empl = new Employee(emplId, emplName, monthlySalary, emplPosition,manangerId,dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empl;

	}

	@Override
	public int createEmployee(Employee e) {
		//public Employee(int id, String name, double monthlySalary, String position, int managerId, Department department)
		int eCreated=0;
		
		String sql= "insert into company.employees (empl_name, monthly_salary, empl_position, manager_id, dept_id) values (?,?,?,?,?);";
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId() );
			ps.setInt(5, e.getDepartment().getId());
			
			eCreated = ps.executeUpdate();

		} catch (SQLException ec) {
			// TODO Auto-generated catch block
			ec.printStackTrace();
		}
		
		return eCreated;
	}

	@Override
	public int updateEmployee(Employee e) {
		//public Employee(int id, String name, double monthlySalary, String position, int managerId, Department department)
		int eUpdated=0;
		String sql= "update company.employees set empl_name= ? , monthly_salary =? , empl_position = ? , manager_id = ? , dept_id=? where empl_id=? ;";
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId() );
			ps.setInt(5, e.getDepartment().getId());
			ps.setInt(6, e.getId());
			
			eUpdated = ps.executeUpdate();
		} catch (SQLException ec) {
			// TODO Auto-generated catch block
			ec.printStackTrace();
		}
		return eUpdated;
	}

	@Override
	public int deleteEmployee(int id) {
		//public Employee(int id, String name, double monthlySalary, String position, int managerId, Department department)
		int eDeleted=0;
		String sql = "delete from company.employees where empl_id = ?";
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);			
			eDeleted = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eDeleted;
	}
	
}
