package com.empsys.ems.manager;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.empsys.ems.employee.Employee;

@Repository
public class ManagerRepository {
    private JdbcClient jdbcClient;
    ManagerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Employee getEmployeeById(int id) {
        return jdbcClient.sql("SELECT id, name, designation, salary, exp  FROM employees WHERE id=:id")
        .param("id",id)
        .query(Employee.class)
        .optional().get();
    }

    public List<Employee> getEmployeeDataAsList() {
        return jdbcClient.sql("SELECT * FROM employees")
        .query(Employee.class)
        .list();
    }

    public void updateEmployeeById(int id, Employee employee) {
        jdbcClient.sql("UPDATE employees set id=?, name=?, designation=?, salary=?, exp=? where id=?")
            .params(List.of(employee.getId(),employee.getName(),employee.getDesignation(),employee.getSalary(),employee.getExp(),id))
            .update();
    }
    public void deleteEmployeeById(int id) {
        jdbcClient.sql("DELETE FROM employees WHERE id=?").param(id).update();
    }
}
