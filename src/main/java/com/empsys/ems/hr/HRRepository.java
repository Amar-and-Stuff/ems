package com.empsys.ems.hr;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class HRRepository {
    // private List<HR> hrs;
    private JdbcClient jdbcClient;
    HRRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        // hrs=new ArrayList<>();
        // hrs.add(new HR(1, "Mounish", "HR", 1000, 0));
        // hrs.add(new HR(2, "Amar", "HR", 1000, 0));
        // hrs.add(new HR(3, "Kiran", "HR", 1000, 0));
        // hrs.add(new HR(4, "Dhanush", "HR", 1000, 0));
        // hrs.add(new HR(5, "Karthik", "HR", 1000, 0));
    }
    // public HR getHRByIdListVersion(int id) {
    //     return hrs.stream().filter(emp->emp.getId()==id).findFirst().get();
    // }

    public HR getHRById(int id) {
        return jdbcClient.sql("SELECT id, name, designation, salary, exp  FROM employees WHERE id=:id")
        .param("id",id)
        .query(HR.class)
        .optional().get();
    }
}