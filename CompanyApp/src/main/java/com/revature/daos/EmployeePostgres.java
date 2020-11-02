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

public class EmployeePostgres implements EmployeeDao{

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from company.employees";
	
	
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while (rs.next()) {
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPosition = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				int dept = rs.getInt("dept_id");
				employees.add(new Employee(emplId, emplName, monthlySalary, emplPosition, managerId, dept);
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
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPosition = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				int dept = rs.getInt("dept_id");

				e = new Employee (emplId,emplName,monthlySalary,emplPosition,managerId,dept);
			}
			
		
		} catch (SQLException excep) {
			// TODO Auto-generated catch block
			excep.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return e;
	}

	@Override
	public int createEmployee(Employee e) {
		// TODO Auto-generated method stub
		int employeeCreated = 0;
		String sql = "insert into company.employees (empl_name, monthly_salary, empl_position, manager_id, dept_id) values (?, ?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			PreparedStatement ps = c.prepareStatement(sql);
		
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			ps.setInt(5, e.getDepartmentId());
			employeeCreated = ps.executeUpdate();
			
		} catch (SQLException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		return employeeCreated;
	}

	@Override
	public int updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		int employeeUpdated = 0;
		String sql = "update company.employees set empl_name = ? , monthly_salary = ?, empl_position= ?, manager_id = ?, dept_id = ? where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			ps.setInt(5, e.getDepartmentId());
			ps.setInt(6, e.getId());
			
			employeeUpdated = ps.executeUpdate();

		} catch (SQLException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
		} catch (IOException a1) {
			// TODO Auto-generated catch block
			a1.printStackTrace();
		}
		
		return employeeUpdated;
	}

	@Override
	public int deleteEmployee(int id) {
		// TODO Auto-generated method stub
		int employeeDeleted = 0;
		String sql = "delete from company.employees where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);
			
			employeeDeleted = ps.executeUpdate();

		} catch (SQLException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
		} catch (IOException a1) {
			// TODO Auto-generated catch block
			a1.printStackTrace();
		}
		
		return employeeDeleted;
	}

}


