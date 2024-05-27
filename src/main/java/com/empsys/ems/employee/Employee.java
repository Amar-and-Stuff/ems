package com.empsys.ems.employee;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private String designation;
    private int salary;
    private float exp;
    

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDesignation() {
        return designation;
    }


    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public int getSalary() {
        return salary;
    }


    public void setSalary(int salary) {
        this.salary = salary;
    }


    public float getExp() {
        return exp;
    }


    public void setExp(float exp) {
        this.exp = exp;
    }


    @Override
    public String toString() {
        return "Employee [ID=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary + ", exp="
                + exp + "]";
    }
    
}
