package com.empsys.ems.manager;

// import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.empsys.ems.employee.Employee;

@Repository
public class ManagerRepository {
    // public List<Manager> managers;
    private JdbcClient jdbcClient;
    ManagerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        // managers=new ArrayList<>();
        // managers.add(new Manager(1, "Mounish", "Employee", 1000, 0));
        // managers.add(new Manager(2, "Amar", "Employee", 1000, 0));
        // managers.add(new Manager(3, "Kiran", "Employee", 1000, 0));
        // managers.add(new Manager(4, "Dhanush", "Employee", 1000, 0));
        // managers.add(new Manager(5, "Karthik", "Employee", 1000, 0));
    }   
    // public Manager getManagerByIdListVersion(int id) {
    //     return managers.stream().filter(emp->emp.getId()==id).findFirst().get();
    // } 

    public Manager getEmployeeById(int id) {
        return jdbcClient.sql("SELECT id, name, designation, salary, exp  FROM employees WHERE id=:id")
        .param("id",id)
        .query(Manager.class)
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
