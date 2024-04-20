package com.empsys.ems.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public class EmployeeRepository {
    private List<Employee> employees;
    EmployeeRepository() {
        employees=new ArrayList<>();
        employees.add(new Employee(1, "Mounish", "Employee", 1000, 0));
        employees.add(new Employee(2, "Amar", "Employee", 1000, 0));
        employees.add(new Employee(3, "Kiran", "Employee", 1000, 0));
        employees.add(new Employee(4, "Dhanush", "Employee", 1000, 0));
        employees.add(new Employee(5, "Karthik", "Employee", 1000, 0));
    }
    public Employee getEmployeeById(int id) {
        return employees.stream().filter(emp->emp.getID()==id).findFirst().get();
    }
}
