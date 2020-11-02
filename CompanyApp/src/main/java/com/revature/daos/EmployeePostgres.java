package com.revature.daos;
import com.revature.models.*;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Department;
import com.revature.services.DepartmentService;
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
				final DepartmentService ds = new DepartmentService();
				int emplId = rs.getInt("dept_id");
				String emplName = rs.getString("empl_name");
				double monthlysalary = rs.getDouble("monthly_salary");
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				Department department = ds.getDepartmentById(rs.getInt("dept_id"));
				
				employees.add(new Employee(emplId, emplName, monthlysalary, position, managerId, department));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee e = null;
		ResultSet rs = null;
		String sql = "select * from company.employees where empl_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				final DepartmentService ds = new DepartmentService();
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double monthlysalary = rs.getDouble("monthly_salary");
				Department department = ds.getDepartmentById(rs.getInt("dept_id"));
				String position = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				e = new Employee(emplId, emplName, monthlysalary, position, managerId, department);

			}
		} catch (SQLException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}

		return e;
	}

	@Override
	public int createEmployee(Employee e) {
		int employeesCreated = 0;
		String sql = "insert into company.employees (empl_name, monthly_salary,empl_position,manager_id,dept_id) values (?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			
			employeesCreated = ps.executeUpdate();

		} catch (SQLException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}
		
		return employeesCreated;
	}

	@Override
	public int updateEmployee(Employee e) {
		int employeesUpdated = 0;
		String sql = "update company.employees set empl_name = ? , monthly_salary = ?, empl_position = ?, manager_id = ?, dept_id =? where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			//position
			ps.setString(3, e.getPosition());
			//manager id
			ps.setInt(4, e.getManagerId());
			//department id
			
			ps.setInt(5, e.getDepartment().getId());
			
			employeesUpdated = ps.executeUpdate();

		} catch (SQLException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
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
