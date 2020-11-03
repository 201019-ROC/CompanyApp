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
import com.revature.util.ConnectionUtil;

public class EmployeePostgres implements EmployeeDao{

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		String sql = "select employees.empl_id, employees.empl_name, employees.monthly_salary, employees.empl_position, " +
						"employees, manager_id, departments.dept_id, departments.dept_name, departments.monthly_budget " +
						"from company.employees join company.departments " +
						"on employees.dept_id = departments.dept_id";

		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int empId = rs.getInt("empl_id");
				String name = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				int deptId= rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double monthlyBudget = rs.getDouble("monthly_budget");				
				Department dept=new Department(deptId, deptName, monthlyBudget);				
				employees.add(new Employee(empId, name, monthlySalary, position, managerId, dept));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		ResultSet rs = null;
		String sql = "select employees.empl_id, employees.empl_name, employees.monthly_salary, employees.empl_position, " +
				"employees, manager_id, departments.dept_id, departments.dept_name, departments.monthly_budget " +
				"from company.employees join company.departments " +
				"on employees.dept_id = departments.dept_id where employees.empl_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt("empl_id");
				String name = rs.getString("empl_name");				
				double monthlySalary = rs.getDouble("monthly_salary");
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double monthlyBudget = rs.getDouble("monthly_budget");				
				Department dept = new Department(deptId, deptName, monthlyBudget);
				employee = new Employee(empId, name, monthlySalary, position, managerId, dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public int createEmployee(Employee e) {
		int employeesCreated = 0;
		String sql = "insert into company.employees " +
				"(empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values (?, ?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			Department dept = e.getDepartment();
			ps.setInt(5, dept.getId());			
			employeesCreated = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return employeesCreated;
	}

	@Override
	public int updateEmployee(Employee e) {
		int employeesUpdated = 0;
		String sql = "update company.employees set empl_name = ? , monthly_salary = ?, empl_position = ?, manager_id = ?, dept_id = ? where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			Department dept = e.getDepartment();
			ps.setInt(5, dept.getId());
			ps.setInt(6, e.getId());
			employeesUpdated = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
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
			e.printStackTrace();
		}
		
		return employeesDeleted;
	}

}
