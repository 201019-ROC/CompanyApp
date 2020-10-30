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
		ResultSet rs = null;
		
		
		String sqlToDept = "select * from company.departments where dept_id = ?";
		Department d = null;
		ResultSet newResult = null;
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPosition = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				int deptId = rs.getInt("dept_id");
				
//				System.out.println("empl_id: " + emplId);
//				System.out.println("empl_name: " + emplName);
//				System.out.println("monthly_salary: " + monthlySalary);
//				System.out.println("empl_position: " + emplPosition);
//				System.out.println("manager_id: " + managerId);
//				System.out.println("dept_id: " + deptId);
				
				
				
				
				PreparedStatement newString = c.prepareStatement(sqlToDept);
				newString.setInt(1, deptId);
//				System.out.println(newString);
				newResult = newString.executeQuery();
				
				while (newResult.next()) {
					int deptId2 = newResult.getInt("dept_id");
					String deptName = newResult.getString("dept_name");
					double budget = newResult.getDouble("monthly_budget");
					d = new Department(deptId2, deptName, budget);
					
//					System.out.println("deptId: " + deptId);
//					System.out.println("dept_name: " + deptName);
//					System.out.println("monthly_budget: " + budget);
				}
				employees.add(new Employee(emplId, emplName, monthlySalary,emplPosition,managerId, d));
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
		// TODO Auto-generated method stub
		Employee theEmployee = null;
		ResultSet rs = null;
		
		String sql = "select * from company.employees where empl_id = ?";
		
		String sqlToDept = "select * from company.departments where dept_id = ?";
		Department d = null;
		ResultSet newResult = null;
		
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
				int deptId = rs.getInt("dept_id");
				
				PreparedStatement newString = c.prepareStatement(sqlToDept);
				newString.setInt(1, deptId);
				newResult = newString.executeQuery();
				
				while (newResult.next()) {
					int deptId2 = newResult.getInt("dept_id");
					String deptName = newResult.getString("dept_name");
					double budget = newResult.getDouble("monthly_budget");
					d = new Department(deptId2, deptName, budget);
				}
				
				theEmployee = new Employee (emplId,emplName,monthlySalary,emplPosition,managerId,d);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return theEmployee;
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
//			System.out.println("Department Id: "+ e.getDepartmentId());
			ps.setInt(5, e.getDepartmentId());
//			System.out.println(ps);
			employeeCreated = ps.executeUpdate();
			
		} catch (SQLException a) {
			// TODO Auto-generated catch block
			a.printStackTrace();
		} catch (IOException a1) {
			// TODO Auto-generated catch block
			a1.printStackTrace();
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
