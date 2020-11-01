package com.revature.daos;

import java.sql.CallableStatement;
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
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double salary = rs.getDouble("monthly_salary");
				String emplPosition = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				int dept = rs.getInt("dept_id");
				employees.add(new Employee(emplId, emplName, salary, emplPosition, managerId, dept));
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
				int emplId = rs.getInt("empl_id");
				String emplName = rs.getString("empl_name");
				double salary = rs.getDouble("monthly_salary");
				String emplPosition = rs.getString("empl_position");
				int managerId = rs.getInt("manager_id");
				int dept = rs.getInt("dept_id");

				e = new Employee(emplId, emplName, salary, emplPosition, managerId, dept);
			}

		} catch (SQLException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}

		return e;
	}

	@Override
	public int createEmployee(Employee e) {

		int employeeCreated = 0;
		String sql = "insert into company.employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values (?, ?, ?, ?, ?)";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			ps.setInt(5, e.getDepartmentId());

			employeeCreated = ps.executeUpdate();

		} catch (SQLException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}

		return employeeCreated;
	}

	@Override
	public int updateEmployee(Employee e) {

		int employeeUpdated = 0;
		String sql = "update company.employees set empl_name = ? , monthly_salary = ? , empl_position = ? , manager_id = ? , dept_id = ? where empl_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, e.getName());
			ps.setDouble(2, e.getMonthlySalary());
			ps.setString(3, e.getPosition());
			ps.setInt(4, e.getManagerId());
			ps.setInt(5, e.getDepartmentId());
			ps.setInt(6, e.getId());

			employeeUpdated = ps.executeUpdate();

		} catch (SQLException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}

		return employeeUpdated;

	}

	@Override
	public int deleteEmployee(int id) {

		int emplDeleted = 0;
		String sql = "delete from company.employees where empl_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);

			emplDeleted = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emplDeleted;
	}

	@Override
	public void prizeEmployee(String department, int prize) {

		String sql = "{call company.prize(?,?)}";

		try (Connection c = ConnectionUtil.getConnection()) {

			CallableStatement cs = c.prepareCall(sql);

			cs.setString(1, department);
			cs.setInt(2, prize);

			cs.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
