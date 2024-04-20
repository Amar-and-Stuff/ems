package com.empsys.ems.hr;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.empsys.ems.employee.Employee;

@Repository
public class HRRepository {
    private JdbcClient jdbcClient;
    HRRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Employee getEmployeeById(int id) {
        return jdbcClient.sql("SELECT id, name, designation, salary, exp  FROM employees WHERE id=:id")
        .param("id",id)
        .query(Employee.class)
        .optional().get();
    }

    public void addEmployee(Employee employee) {
        jdbcClient.sql("INSERT INTO employees (id, name, designation, salary, exp) VALUES(?, ?, ?, ?, ?)")
        .params(List.of(employee.getId(),employee.getName(),employee.getDesignation(),employee.getSalary(),employee.getExp()))
        .update();
    }
}