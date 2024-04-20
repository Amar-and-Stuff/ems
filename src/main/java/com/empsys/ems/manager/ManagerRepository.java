package com.empsys.ems.manager;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

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

    public Manager getManagerById(int id) {
        return jdbcClient.sql("SELECT id, name, designation, salary, exp  FROM employees WHERE id=:id")
        .param("id",id)
        .query(Manager.class)
        .optional().get();
    }
}
