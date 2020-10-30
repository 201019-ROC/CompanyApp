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
		String sql = "select * from company.employees";

		try (Connection c = ConnectionUtil.getHardCodedConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int employeeId = rs.getInt("empl_id");
				String employeeName = rs.getString("empl_name");
				double salary = rs.getDouble("monthly_salary");
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				
				int deptId = rs.getInt("dept_id");
				ResultSet rs2 = null;
				String sql2 = "select * from company.departments where dept_id = ?";

				try (Connection c2 = ConnectionUtil.getHardCodedConnection()) {
					PreparedStatement ps2 = c2.prepareStatement(sql2);
					ps2.setInt(1, deptId);
					rs2 = ps2.executeQuery();

					while (rs2.next()) {
						String deptName = rs2.getString("dept_name");
						double budget = rs2.getDouble("monthly_budget");
						Department department = new Department(deptId, deptName, budget);
						Employee employee = new Employee(employeeName, salary, position, managerId, department);
						employee.setId(employeeId);
						employees.add(employee);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		ResultSet rs = null;
		String sql = "select * from company.employees where empl_id = ?";

		try (Connection c = ConnectionUtil.getHardCodedConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("empl_id");
				String employeeName = rs.getString("empl_name");
				double salary = rs.getDouble("monthly_salary");
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				
				int deptId = rs.getInt("dept_id");
				ResultSet rs2 = null;
				String sql2 = "select * from company.departments where dept_id = ?";
				
				try (Connection c2 = ConnectionUtil.getHardCodedConnection()) {
					PreparedStatement ps2 = c2.prepareStatement(sql2);
					ps2.setInt(1, deptId);
					rs2 = ps2.executeQuery();

					while (rs2.next()) {
						String deptName = rs2.getString("dept_name");
						double budget = rs2.getDouble("monthly_budget");
						Department department = new Department(deptId, deptName, budget);
						employee = new Employee(employeeName, salary, position, managerId, department);
						employee.setId(employeeId);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;

	}

	@Override
	public int createEmployee(Employee empl) {
		int employeesCreated = 0;
		String sql = "insert into company.employees (empl_name, monthly_salary, empl_position, manager_id, dept_id) values (?, ?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getHardCodedConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, empl.getName());
			ps.setDouble(2, empl.getMonthlySalary());
			ps.setString(3, empl.getPosition());
			ps.setInt(4, empl.getManagerId());
			ps.setInt(5,  empl.getDepartment().getId());
			
			employeesCreated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeesCreated;
	}

	@Override
	public int updateEmployee(Employee empl) {
		int employeesUpdated = 0;
		String sql = "update company.employees set empl_name = ? , monthly_salary = ? , empl_position = ? , manager_id = ?, dept_id = ? where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getHardCodedConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, empl.getName());
			ps.setDouble(2, empl.getMonthlySalary());
			ps.setString(3, empl.getPosition());
			ps.setInt(4, empl.getManagerId());
			ps.setInt(5, empl.getDepartment().getId());
			ps.setInt(6,  empl.getId());
			
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
		
		try (Connection c = ConnectionUtil.getHardCodedConnection()) {
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
