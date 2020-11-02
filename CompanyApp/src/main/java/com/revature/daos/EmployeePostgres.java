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
	
	public List<Employee> getEmployees()
	{

			List<Employee> employees = new ArrayList<>();
			String sql = "select * from company.employees";

			try (Connection c = ConnectionUtil.getConnection()) {
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);

				while (rs.next()) {
					String emplName = rs.getString("empl_name");
					double salary = rs.getDouble("monthly_salary");
					String pos = rs.getString("empl_position");
					int mang = rs.getInt("manager_id");
					int dept = rs.getInt("dept_id");
					employees.add(new Employee(emplName, salary, pos, mang, dept));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return employees;
		}
	
	
	
	public Employee getEmployeeById(int id)
	{
		Employee em = null;
		String sql = " select * from company.employees where empl_id=?";
		 
		try(Connection c= ConnectionUtil.getConnection())
		{
		PreparedStatement ps= c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			String emplName = rs.getString("empl_name");
			double salary = rs.getDouble("monthly_salary");
			String pos = rs.getString("empl_position");
			int mang = rs.getInt("manager_id");
			int dept = rs.getInt("dept_id");
			em= new Employee(emplName, salary, pos, mang, dept);
			
		}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return em;
		
	}
	
	
	public int createEmployee(Employee e)
	{
		int employeesCreated=0;
		String sql = "INSERT INTO company.employees "
				+ "(empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id)"
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection c= ConnectionUtil.getConnection())
		{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			ps.setInt(5, e.getDepartmentId());
			employeesCreated = ps.executeUpdate();
			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return employeesCreated;
	
	}
	public int updateEmployee(Employee e)
	{
		int employeesUpdated=0;
		String sql = "UPDATE company.employees "
				+ "SET empl_name=?, monthly_salary=?, empl_position=?, manager_id=?, dept_id=? WHERE empl_id=?";
		try (Connection c= ConnectionUtil.getConnection())
		{
			PreparedStatement ps= c.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			ps.setInt(5, e.getDepartmentId());
			ps.setInt(6, e.getId());
			employeesUpdated = ps.executeUpdate();
			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}

		return employeesUpdated;
	}
	public int deleteEmployee(int id)
	{
		int employeeDeleted = 0;
		String sql = "delete from company.employees where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);
			
			employeeDeleted = ps.executeUpdate();

		} catch (SQLException a) 
		{
			// TODO Auto-generated catch block
			a.printStackTrace();
		}
		return employeeDeleted;
	}
}

