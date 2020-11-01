package com.revature.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.util.ConnectionUtil;

public class EmployeePostgres implements EmployeeDao {
	
	private static DepartmentService ds = new DepartmentService();
	
	@Override
	public List<Employee> getEmployees() {

		List<Employee> employees = new ArrayList<>();
		String sql = "select * from company.employees";

		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPosition = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				Department department = ds.getDepartmentById(rs.getInt("dept_id"));
				employees.add(new Employee(emplId, emplName, monthlySalary,emplPosition,managerId, department));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee empl = null;
		ResultSet rs = null;
		String sql = "select * from company.employees where empl_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPosition = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				Department department = ds.getDepartmentById(rs.getInt("dept_id"));
				empl = new Employee(emplId, emplName, monthlySalary,emplPosition,managerId, department);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empl;
	}

	@Override
	public int createEmployee(Employee empl) {
		int employeeCreated = 0;
		String sql = "insert into company.employees (empl_name, monthly_salary,empl_position, manager_id) values (?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, empl.getName());
			ps.setDouble(2, empl.getMonthlySalary());
			ps.setString(3, empl.getPosition());
			ps.setInt(4, empl.getManagerId());
			
			employeeCreated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeCreated;
	}

	@Override
	public int updateEmployee(Employee empl) {
		int employeeUpdated = 0;
		String sql = "update company.employees set empl_name = ? , monthly_salary = ?, empl_position = ?, manager_id = ? where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, empl.getName());
			ps.setDouble(2, empl.getMonthlySalary());
			ps.setString(3, empl.getPosition());
			ps.setInt(4, empl.getManagerId());
			
			employeeUpdated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeUpdated;
	}

	@Override
	public int deleteEmployee(int id) {
		int employeeDeleted = 0;
		String sql = "delete from company.employees where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			employeeDeleted = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeDeleted;
	}
	

}
