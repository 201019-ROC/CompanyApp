package com.revature.services;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeesPostgres;
import com.revature.models.Employee;

import java.util.List;

public class EmployeeService {

    private static EmployeeDao ed = new EmployeesPostgres();

    public List<Employee> getEmployees() {
        return ed.getEmployees();
    }

    public Employee getEmployeeById(int id) {
        return ed.getEmployeeById(id);
    }

    public boolean createEmployee(Employee e) {
        int createdEmployee = ed.createEmployee(e);
        if (createdEmployee != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateEmployee(Employee e) {
        int empUpdated = ed.updateEmployee(e);
        if (empUpdated != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        int emplDeleted = ed.deleteEmployee(id);
        if (emplDeleted != 0) {
            return true;
        } else {
            return false;
        }
    }
}
