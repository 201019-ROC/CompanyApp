package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeePostgres implements EmployeeDao {

	@Override
	public List<Employee> getEmployees() {
		
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from company.employees";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int empl_id = rs.getInt("empl_id");
				String empl_name = rs.getString("empl_name");
				double monthly_salary = rs.getDouble("monthly_salary");
				String empl_position = rs.getString("empl_position");
				int dept_id = rs.getInt("dept_id");
				
				employees.add(new Employee(empl_id, empl_name, monthly_salary, empl_position, dept_id));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee e = null;
		ResultSet rs = null;
		String sql = "select * from company.employees where empl_id = ?";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int empl_id = rs.getInt("empl_id");
				String empl_name = rs.getString("empl_name");
				double monthly_salary = rs.getDouble("monthly_salary");
				String empl_position = rs.getString("empl_position");
				int dept_id = rs.getInt("dept_id");

				e = new Employee(empl_id, empl_name, monthly_salary, empl_position, dept_id);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return e;
	}

	@Override
	public int createEmployee(Employee e) {
		int EmployeesCreated = 0;
		//my database does not include string departments or int dept_id for some reason so they are exclued
		String sql = "insert into company.employees (empl_id, empl_name, monthly_salary, empl_position) values (?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setDouble(3, e.getMonthlySalary());
			ps.setString(4, e.getPosition());
			
			
			
			EmployeesCreated = ps.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return EmployeesCreated;
	
	}

	@Override
	public int updateEmployee(Employee e) {
			int employeesUpdated = 0;
			String sql = "update company.employees set empl_name = ? , monthly_salary = ?, empl_position = ? where empl_id = ?";
			
			try (Connection c = ConnectionUtil.getConnectionFromFile()) {
				PreparedStatement ps = c.prepareStatement(sql);
				
				ps.setString(1, e.getName());
				ps.setDouble(2, e.getMonthlySalary());
				ps.setString(3, e.getPosition());
				ps.setInt(4, e.getId());
				
				employeesUpdated = ps.executeUpdate();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			return employeesUpdated;
	}

	@Override
	public int deleteEmployee(int id) {
		int employeesDeleted = 0;
		String sql = "delete from company.employees where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			employeesDeleted = ps.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return employeesDeleted;
	}

}
