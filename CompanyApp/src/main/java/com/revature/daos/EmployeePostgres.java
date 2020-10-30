package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.MonthlySalaryException;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeePostgres implements EmployeeDao {

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from company.employees join company.departments on company.employees.dept_id = company.departments.dept_id;";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlySalary = rs.getDouble("monthly_salary");
				String emplPos = rs.getString("empl_position");
				Integer managerId = rs.getObject("manager_id", Integer.class);
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double budget = rs.getDouble("monthly_budget");
				employees.add(new Employee(emplId, emplName, monthlySalary, emplPos, managerId,
						new Department(deptId, deptName, budget)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MonthlySalaryException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = new Employee();
		Department d = new Department();
		ResultSet rs = null;
		
		String sqlE = "select * from company.employees where empl_id = ?";
		String sqlD = "select dept_name, monthly_budget from company.departments where dept_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sqlE);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				employee.setId(rs.getInt("empl_id"));
				employee.setName(rs.getString("empl_name"));
				employee.setMonthlySalary(rs.getDouble("monthly_salary"));
				employee.setPosition(rs.getString("empl_position"));
				employee.setManagerId(rs.getObject("manager_id", Integer.class));
				d.setId(rs.getInt("dept_id"));
			}
			
			PreparedStatement psD = c.prepareStatement(sqlD);
			psD.setInt(1, d.getId());
			rs = psD.executeQuery();
			
			while(rs.next()) {
				d.setName(rs.getString("dept_name"));
				d.setMonthlyBudget(rs.getDouble("monthly_budget"));
			}
			
			employee.setDepartment(d);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MonthlySalaryException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
		return employee;
	}

	@Override
	public int createEmployee(Employee e) {
		int employeesCreated = 0;
		String sql = "insert into company.employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values (?, ?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setObject(4, e.getManagerId());
			ps.setInt(5, e.getDepartment().getId());
			
			employeesCreated = ps.executeUpdate();

		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		
		return employeesCreated;
	}

	@Override
	public int updateEmployee(Employee e) {
		int employeesUpdated = 0;
		String sql = "UPDATE company.employees SET empl_name=?, monthly_salary=?, empl_position=?, manager_id=?, dept_id=? WHERE empl_id=?";
				
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setObject(4, e.getManagerId());
			ps.setInt(5, e.getDepartment().getId());
			ps.setInt(6, e.getId());
			
			
			employeesUpdated = ps.executeUpdate();

		} catch (SQLException exp) {
			exp.printStackTrace();
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
