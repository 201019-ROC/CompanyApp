package com.revature.daos;

import java.io.IOException;
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
import com.revature.util.ConnectionUtil;

public class DepartmentPostgres implements DepartmentDao {

	@Override
	public List<Department> getDepartments() {

		List<Department> departments = new ArrayList<>();
		String sql = "select * from company.departments";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double budget = rs.getDouble("monthly_budget");
				departments.add(new Department(deptId, deptName, budget));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		Department d = null;
		ResultSet rs = null;
		String sql = "select * from company.departments where dept_id = ?";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double budget = rs.getDouble("monthly_budget");
				d = new Department(deptId, deptName, budget);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return d;
	}

	@Override
	public int createDepartment(Department d) {
		int departmentsCreated = 0;
		String sql = "insert into company.departments (dept_name, monthly_budget) values (?, ?)";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, d.getName());
			ps.setDouble(2, d.getMonthlyBudget());
			
			departmentsCreated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return departmentsCreated;
	}

	@Override
	public int updateDepartment(Department d) {
		int departmentsUpdated = 0;
		String sql = "update company.departments set dept_name = ? , monthly_budget = ? where dept_id = ?";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, d.getName());
			ps.setDouble(2, d.getMonthlyBudget());
			ps.setInt(3, d.getId());
			
			departmentsUpdated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return departmentsUpdated;
	}

	@Override
	public int deleteDepartment(int id) {
		int departmentsDeleted = 0;
		String sql = "delete from company.departments where dept_id = ?";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			departmentsDeleted = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return departmentsDeleted;
	}

	@Override
	public void budgetIncrease(double increase, int id) {
		String sql = "{call company.increase_budget(?,?)}";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()){
			CallableStatement cs = c.prepareCall(sql);
			cs.setBigDecimal(1, new BigDecimal(increase));
			cs.setInt(2, id);
			
			cs.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
