package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int empl_id = rs.getInt("empl_id");
				String name = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				
				
				int departmentId = rs.getInt("dept_Id");
				sql = "select * from company.departments where dept_id = ?";
				ps = c.prepareStatement(sql);
				ps.setInt(1, departmentId);
				ResultSet rsdepartment = ps.executeQuery();
				Department department = new Department();
				
				while (rsdepartment.next()) {
					int deptId = rsdepartment.getInt("dept_id");
					String deptName = rsdepartment.getString("dept_name");
					double budget = rsdepartment.getDouble("monthly_budget");
					department = new Department(deptId, deptName, budget);
				}				
				
				employees.add(new Employee(empl_id, name, monthlySalary, position, managerId, department));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		Employee employees = new Employee();
		String sql = "select * from company.employees where empl_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int empl_id = rs.getInt("empl_id");
				String name = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				
				
				int departmentId = rs.getInt("dept_Id");
				sql = "select * from company.departments where dept_id = ?";
				ps = c.prepareStatement(sql);
				ps.setInt(1, departmentId);
				ResultSet rsdepartment = ps.executeQuery();
				Department department = new Department();
				
				while (rsdepartment.next()) {
					int deptId = rsdepartment.getInt("dept_id");
					String deptName = rsdepartment.getString("dept_name");
					double budget = rsdepartment.getDouble("monthly_budget");
					department = new Department(deptId, deptName, budget);
				}				
				
				employees = new Employee(empl_id, name, monthlySalary, position, managerId, department);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public int createEmployee(Employee e) {
		// TODO Auto-generated method stub
		String sql = "insert into company.employees (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_Id) values (?,?,?,?,?,?)";
		int result = 0;
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setDouble(3, e.getMonthlySalary());
			ps.setString(4, e.getPosition());
			ps.setInt(5, e.getManagerId());
			ps.setInt(6, e.getDepartment().getId());
			result = ps.executeUpdate();

			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return result;
	}

	@Override
	public int updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		String sql = "update company.employees set empl_name = ?, monthly_salary = ?, empl_position = ?, manager_id = ?, dept_Id = ? where empl_id = ?";
		int result = 0;
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			ps.setInt(5, e.getDepartment().getId());
			ps.setInt(6, e.getId());
			result = ps.executeUpdate();

			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return result;
	}

	@Override
	public int deleteEmployee(int id) {
		String sql = "delete from company.employees where empl_id = ?";
		int result = 0;
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return result;
	}

}
