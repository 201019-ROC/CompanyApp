package com.revature.daos;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesPostgres  implements EmployeeDao {
    @Override
    public List<Employee> getEmployees() {

        List<Employee> employees = new ArrayList<>();
        String sql = "Select * from company.employees";

        try (Connection c = ConnectionUtil.getConnectionFromFile()){
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()){
                int emplId = rs.getInt("empl_id");
                String emplName = rs.getString("empl_name");
                double monthlySalary = rs.getInt("monthly_salary");
                String emplPosition = rs.getString("empl_position");
                int managerId = rs.getInt("manager_id");
                int departmentId = rs.getInt("dept_id");
                employees.add( new Employee(emplId, emplName, monthlySalary, emplPosition, managerId, departmentId));

            }
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee e = null;
        ResultSet rs = null;
        String sql = "select * from company.employees where empl_id = ?";

        try (Connection c = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                int emplId = rs.getInt("empl_id");
                String emplName = rs.getString("empl_name");
                double monthlySalary = rs.getInt("monthly_salary");
                String emplPosition = rs.getString("empl_position");
                int managerId = rs.getInt("manager_id");
                e = new Employee(emplId, emplName, monthlySalary,emplPosition,managerId);
            }
        } catch (SQLException | IOException x){
            x.printStackTrace();
        }
        return e;
    }

    @Override
    public int createEmployee(Employee e) {
        int employeeCreated = 0;
        String sql = "insert into company.employees (empl_name, monthly_salary, empl_position, manager_id, dept_id) values (?,?,?,?,?)";

        try( Connection c = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1,e.getName());
            ps.setDouble(2, e.getMonthlySalary());
            ps.setString(3,e.getPosition());
            ps.setInt(4, e.getManagerId());
            ps.setInt(5,e.getDeptId());

            employeeCreated = ps.executeUpdate();

        } catch (SQLException | IOException x){
            x.printStackTrace();

        }
        return employeeCreated;

    }


// empl_name, monthly_salary, empl_position, manager_id, dept_id
    @Override
    public int updateEmployee(Employee e) {
        int employeeUpdated = 0;
        String sql = "update company.employees set empl_name = ?, monthly_salary = ?, empl_position =?  where empl_id =  ?";

        try (Connection c = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, e.getName());
            ps.setDouble(2 , e.getMonthlySalary());
            ps.setString(3, e.getPosition());
            ps.setInt(4,e.getId());

            employeeUpdated= ps.executeUpdate();

        } catch (SQLException | IOException x) {
            x.printStackTrace();
        }
        return employeeUpdated;
    }

    @Override
    public int deleteEmployee(int id) {
        int employeeDeleted = 0;
        String sql = "delete from company.employees where empl_id =? ";

        try( Connection c = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, id);

            employeeDeleted = ps.executeUpdate();
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return employeeDeleted;
    }
}
