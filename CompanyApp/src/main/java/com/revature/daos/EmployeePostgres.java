package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeePostgres implements EmployeeDao {

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from company.employees";

		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int empId = rs.getInt("empl_id");
				String name = rs.getString("empl_name");
				String posistion = rs.getString("empl_position");
				double salary = rs.getDouble("monthly_salary");
				employees.add(new Employee(empId, name, posistion, salary));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee emp = null;
		ResultSet rs = null;
		String sql = "select * from company.employees where emp_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt("empl_id");
				String name = rs.getString("empl_name");
				String position = rs.getString("empl_position");
				double salary = rs.getDouble("monthly_salary");
				emp = new Employee(empId, name, position, salary);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emp;
	}

	@Override
	public int createEmployee(Employee emp) {
		int employeesCreated = 0;
		String sql = "insert into company.employees(empl_name, empl_position, monthly_salary) values (?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getPosition());
			ps.setDouble(3, emp.getMonthlySalary());
			
			employeesCreated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeesCreated;
	}

	@Override
	public int updateEmployee(Employee emp) {
		int employeesUpdated = 0;
		String sql = "update company.employees set empl_name = ? , empl_position = ? , monthly_salary = ? where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getPosition());
			ps.setDouble(3, emp.getMonthlySalary());
			ps.setInt(4, emp.getId());
			
			employeesUpdated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeesUpdated;
	}

	@Override
	public int deleteEmployee(int id) {
		int employeesDeleted = 0;
		String sql = "delete from company.employees where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			employeesDeleted = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeesDeleted;
	}

}
